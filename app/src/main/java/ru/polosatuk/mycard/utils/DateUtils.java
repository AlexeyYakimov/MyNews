package ru.polosatuk.mycard.utils;


import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import ru.polosatuk.mycard.R;

public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM hh:mm a, EEEE";
    private static final String TIME_FORMAT = " HH:mm a";
    private static  SimpleDateFormat simpleDate = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
    private static  SimpleDateFormat defaultDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault());

    private static Calendar calendar = Calendar.getInstance(Locale.getDefault());
    private static int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    private static int currentMonth = calendar.get(Calendar.MONTH);
    private static int currentYear = calendar.get(Calendar.YEAR);
    private static Calendar localDate = Calendar.getInstance();

    private DateUtils() {
        throw new IllegalAccessError("No instance");
    }

    @NonNull
    public static String getDateToNews(@NonNull Date date,
                                       @NonNull Context context) {
        localDate.setTime(date);
        localDate.setTimeZone(TimeZone.getDefault());
        if (isThisMonth(localDate)) {
            if (currentDay == localDate.get(Calendar.DAY_OF_MONTH)) {
                long a = calendar.getTimeInMillis() - localDate.getTimeInMillis();

                return String.valueOf(TimeUnit.MILLISECONDS.toHours(a)) +" " +
                        context.getString(R.string.date_view_pattern_hr_ago) +
                        simpleDate.format(date);

            } else if ((currentDay - 1) == localDate.get(Calendar.DAY_OF_MONTH))
                return context.getString(R.string.yesterday_date_view_pattern) + " " + simpleDate.format(date);

        } else {
            return defaultDate.format(date);
        }
        return defaultDate.format(date);
    }

    public static String getNewsDetailDate(@NonNull Date date){
        return defaultDate.format(date);
    }

    private static boolean isThisMonth(@NonNull Calendar c) {
        return currentMonth == c.get(Calendar.MONTH) &&
                currentYear == c.get(Calendar.YEAR);

    }


}
