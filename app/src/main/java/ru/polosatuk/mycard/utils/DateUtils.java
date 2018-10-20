package ru.polosatuk.mycard.utils;


import android.content.Context;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.security.PrivilegedExceptionAction;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import ru.polosatuk.mycard.R;

public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM HH:mm a, EEEE";
    private static final String TIME_FORMAT = " HH:mm a";
    private static SimpleDateFormat simpleDate = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
    private static SimpleDateFormat defaultDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault());

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private static LocalDateTime currentTime = LocalDateTime.now();
//    private static int currentDay = currentTime.getDayOfMonth();
//    private static int currentMonth = currentTime.getMonthValue();
//    private static int currentYear = currentTime.getYear();
//    private static LocalDateTime localDate;

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

                return context.getString(R.string.date_view_pattern_hr_ago,
                        String.valueOf(TimeUnit.MILLISECONDS.toHours(a)),
                        simpleDate.format(date));

            } else if ((currentDay - 1) == localDate.get(Calendar.DAY_OF_MONTH)) {
                return context.getString(R.string.yesterday_date_view_pattern, currentTime.format(formatter));
//                return context.getString(R.string.yesterday_date_view_pattern, simpleDate.format(date));
            }
        }
        return defaultDate.format(date);
    }

    public static String getNewsDetailDate(@NonNull Date date) {
        return defaultDate.format(date);
    }

    private static boolean isThisMonth(@NonNull Calendar c) {
        return currentMonth == c.get(Calendar.MONTH) &&
                currentYear == c.get(Calendar.YEAR);

    }

    @NonNull
    public static Date createDate(int year, int month, int date, int hrs, int min) {
        return new GregorianCalendar(year, month, date, hrs, min).getTime();
    }


}
