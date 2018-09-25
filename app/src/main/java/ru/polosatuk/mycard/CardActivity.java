package ru.polosatuk.mycard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CardActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView telegrammClick;
    private ImageView vkClick;
    private ImageView gitClick;
    private ImageView sendMessageClick;
    private EditText textMessage;

    String linkToGit = "https://github.com/PoLoSkA";
    String linkToVK = "https://vk.com/smugas";
    String linkToTelegramm = "https://t.me/polosatuk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        init();
    }

    private void init(){
        textMessage = findViewById(R.id.textMessage);
        gitClick = findViewById(R.id.git_click);
        vkClick = findViewById(R.id.vk_click);
        telegrammClick = findViewById(R.id.telegramm_click);
        sendMessageClick = findViewById(R.id.sendMessage);
        gitClick.setOnClickListener(this);
        vkClick.setOnClickListener(this);
        telegrammClick.setOnClickListener(this);
        sendMessageClick.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sendMessage:
                 String message =textMessage.getText().toString();
                if (message.trim().length() > 0){
                    sendSms(message);
                }
                break;
            case R.id.git_click:
                goToBrowser(linkToGit);
                break;
            case R.id.vk_click:
                goToBrowser(linkToVK);
                break;
            case R.id.telegramm_click:
                goToBrowser(linkToTelegramm);
                break;

        }

    }

    private void goToBrowser(String link) {
        Intent goToGit = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        if (goToGit.resolveActivity(getPackageManager()) != null)
            startActivity(goToGit);
        else
            Toast.makeText(this, R.string.no_app, Toast.LENGTH_LONG).show();
    }

    private void sendSms(String message){
        String phoneNumber = "+79653811975";
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address",phoneNumber);
        smsIntent.putExtra("sms_body",message);
        if (smsIntent.resolveActivity(getPackageManager()) != null)
            startActivity(smsIntent);
        else
            Toast.makeText(this, R.string.no_app, Toast.LENGTH_LONG).show();
    }
}
