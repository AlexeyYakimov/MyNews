package ru.polosatuk.mycard;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

class ThirdPartyIntentUtils {
    private static final String PHONE_NUMBER = "+79653811975";
    private static final String SMS_TYPE = "smsto:";
    private static final String SMS_BODY_EXTRA = "sms_body";


    static Intent getSmsIntent(@NonNull String message) {

        Uri smsInfo = Uri.parse(SMS_TYPE + PHONE_NUMBER);
        Intent intent = new Intent(Intent.ACTION_VIEW, smsInfo);
        intent.putExtra(SMS_BODY_EXTRA, message);
        return intent;
    }

    static Intent getGoToBrowser(@NonNull String link) {

        return new Intent(Intent.ACTION_VIEW, Uri.parse(link));
    }
}
