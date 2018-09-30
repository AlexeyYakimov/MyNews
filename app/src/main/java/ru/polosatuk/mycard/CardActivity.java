package ru.polosatuk.mycard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LINK_TO_GIT = "https://github.com/PoLoSkA";
    private static final String LINK_TO_VK = "https://vk.com/smugas";
    private static final String LINK_TO_TELEGRAM = "https://t.me/polosatuk";


    private EditText textMessage;
    private RelativeLayout mainLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
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

        textMessage = findViewById(R.id.textMessage);

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
                sendSms(view);
            }
            break;
            case R.id.git_click: {
                goToBrowser(LINK_TO_GIT, view);
            }
            break;
            case R.id.vk_click: {
                goToBrowser(LINK_TO_VK, view);
            }
            break;
            case R.id.telegram_click: {
                goToBrowser(LINK_TO_TELEGRAM, view);
            }
            break;
            default: {
                Log.d("MainActivity", "click on unknown view");
            }
            break;
        }

    }

    private void goToBrowser(@NonNull String link, @NonNull View view) {

        Intent goToBrowser = ThirdPartyIntentUtils.getGoToBrowser(link);
        if (goToBrowser != null)
            startActivity(goToBrowser);
        else {
            Snackbar.make(view, R.string.no_app, Snackbar.LENGTH_LONG).show();
        }
    }

    private void sendSms(@NonNull View view) {

        String message = textMessage.getText().toString();
        if (message.trim().length() > 0) {
            Intent smsIntent = ThirdPartyIntentUtils.getSmsIntent(message);
            if (smsIntent != null) {
                startActivity(smsIntent);
            } else {
                Snackbar.make(view, R.string.no_app, Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(view, R.string.enter_app_text, Snackbar.LENGTH_LONG).show();
        }
    }
}
