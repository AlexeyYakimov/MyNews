package ru.polosatuk.mycard.utils;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.GregorianCalendar;

import static android.text.format.DateUtils.DAY_IN_MILLIS;
import static android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE;
import static android.text.format.DateUtils.HOUR_IN_MILLIS;

public class DateUtils {

    private DateUtils() {
        throw new IllegalAccessError("No instance");
    }

    public static CharSequence formatDateTime(Context context, Date dateTime) {
        return android.text.format.DateUtils.getRelativeDateTimeString(
                context,
                dateTime.getTime(),
                HOUR_IN_MILLIS,
                5 * DAY_IN_MILLIS,
                FORMAT_ABBREV_RELATIVE
        );
    }

    @NonNull
    public static Date createDate(int year, int month, int date, int hrs, int min) {
        return new GregorianCalendar(year, month, date, hrs, min).getTime();
    }




}
