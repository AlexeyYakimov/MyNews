package ru.polosatuk.mycard.screen.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import ru.polosatuk.mycard.R;

public class ImageUtils {
    private ImageUtils() {
        throw new IllegalAccessError("No exist");
    }

    @NonNull
    public static RequestManager getRequestManager(@NonNull Context context) {
        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.place_holder)
                .fallback(R.drawable.place_holder)
                .centerCrop();
        return Glide.with(context).applyDefaultRequestOptions(imageOption);
    }
}
