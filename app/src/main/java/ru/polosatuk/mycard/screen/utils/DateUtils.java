package ru.polosatuk.mycard.screen.utils;


import android.content.Context;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.polosatuk.mycard.App;

import static android.text.format.DateUtils.DAY_IN_MILLIS;
import static android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE;
import static android.text.format.DateUtils.HOUR_IN_MILLIS;
import static android.text.format.DateUtils.getRelativeDateTimeString;

public class DateUtils {
    @NonNull
    private Context context;
    private static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";//"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"  2018-12-05T00:42:26-05:00

    public DateUtils(@NonNull Context context) {
        this.context = context;
    }

    public CharSequence formatDateTime(String date) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            Date dateTime = format.parse(date);

            return getRelativeDateTimeString(
                    context,
                    dateTime.getTime(),
                    HOUR_IN_MILLIS,
                    5 * DAY_IN_MILLIS,
                    FORMAT_ABBREV_RELATIVE
            );
        } catch (ParseException e) {
            return date;
        }
    }

}
