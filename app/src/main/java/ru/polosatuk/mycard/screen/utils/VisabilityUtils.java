package ru.polosatuk.mycard.screen.utils;

import android.support.annotation.Nullable;
import android.view.View;


public class VisabilityUtils {
    public static void setVisible(@Nullable View view, boolean show) {
        if (view == null) return;

        int visibility = show ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }
}
