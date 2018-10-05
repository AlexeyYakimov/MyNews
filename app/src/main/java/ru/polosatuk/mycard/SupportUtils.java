package ru.polosatuk.mycard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SupportUtils {

    public static String getSimpleDate(Date date) {
        return new SimpleDateFormat("EEEE, dd MMM hh:mm a").format(date);
    }
}
