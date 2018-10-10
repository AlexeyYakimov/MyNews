package ru.polosatuk.mycard.news.data;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import androidx.annotation.NonNull;

public class NewsConverter {
    public static final String NEWS_KEY_EXTRA = "news_details_key_extra";
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    @NonNull
    public static String newsToJson(NewsItem newsItem) {

        return gson.toJson(newsItem);
    }

    public static NewsItem getNewsDetailFromGson(@NonNull Intent intent, @NonNull Type type) {
        return gson.fromJson(intent.getStringExtra(NEWS_KEY_EXTRA), type);
    }

}
