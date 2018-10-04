package ru.polosatuk.mycard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LINK_TO_GIT = "http://github.com/PoLoSkA";
    private static final String LINK_TO_VK = "http://vk.com/smugas";
    private static final String LINK_TO_TELEGRAM = "http://t.me/polosatuk";


    private EditText textMessage;
    private RelativeLayout mainLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
        disclaimer();
    }

    private void disclaimer() {
        TextView tvDisclaimer = new TextView(this);

        tvDisclaimer.setText(getString(R.string.disclaimer));
        tvDisclaimer.setLayoutParams(getDefaultParamsRelativeLayout());

        mainLayout.addView(tvDisclaimer);
    }

    @NonNull
    private RelativeLayout.LayoutParams getDefaultParamsRelativeLayout() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT); // height
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        return params;
    }

    private void init() {

        mainLayout = findViewById(R.id.content_main);

        textMessage = findViewById(R.id.text_message);

        ImageView gitClick = findViewById(R.id.git_click);
        ImageView vkClick = findViewById(R.id.vk_click);
        ImageView telegramClick = findViewById(R.id.telegram_click);
        ImageView sendMessageClick = findViewById(R.id.send_message);

        gitClick.setOnClickListener(this);
        vkClick.setOnClickListener(this);
        telegramClick.setOnClickListener(this);
        sendMessageClick.setOnClickListener(this);


    }

    @Override
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.send_message: {
                sendSms();
            }
            break;
            case R.id.git_click: {
                getOpenBrowserIntent(LINK_TO_GIT);
            }
            break;
            case R.id.vk_click: {
                getOpenBrowserIntent(LINK_TO_VK);
            }
            break;
            case R.id.telegram_click: {
                getOpenBrowserIntent(LINK_TO_TELEGRAM);
            }
            break;
            default: {
                Log.d("MainActivity", "click on unknown view");
            }
            break;
        }

    }

    private void getOpenBrowserIntent(@NonNull String link) {

        Intent goToBrowser = ThirdPartyIntentUtils.getGoToBrowser(this, link);
        if (goToBrowser != null)
            startActivity(goToBrowser);
        else {
            showErrorSnackbar(R.string.no_app);
        }
    }

    private void sendSms() {

        String message = textMessage.getText().toString();
        if (message.trim().length() > 0) {
            Intent smsIntent = ThirdPartyIntentUtils.getSmsIntent(this, message);
            if (smsIntent != null) {
                startActivity(smsIntent);
            } else {
                showErrorSnackbar(R.string.no_app);
            }
        } else {
            showErrorSnackbar(R.string.enter_app_text);
        }
    }

    public void showErrorSnackbar(int errorMessage) {

        View view = findViewById(R.id.content);
        if (view != null) {
            Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show();
        }
    }

}
