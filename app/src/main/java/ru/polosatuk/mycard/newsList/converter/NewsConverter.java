package ru.polosatuk.mycard.newsList.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.polosatuk.mycard.App;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.newsList.models.NewsItem;
import ru.polosatuk.mycard.utils.DateUtils;

public class NewsConverter {
    @NonNull
    private static GsonBuilder builder = new GsonBuilder();
    @NonNull
    private static Gson gson = builder.create();

    private NewsConverter() {
    }

    @NonNull
    public static String newsToJson(NewsDisplayableModel newsItem) {

        return gson.toJson(newsItem);
    }

    @Nullable
    public static NewsDisplayableModel fromJson(@NonNull String json) {
        try {
            return gson.fromJson(json, NewsDisplayableModel.class);
        } catch (JsonSyntaxException e) {
            Log.d("NewsConverter", "Json syntax incorrect");
            return null;
        } catch (JsonParseException e) {
            Log.d("NewsConverter", "Json parse");
            return null;
        }
    }

    @NonNull
    private static String getNewsDate(Date date) {
        return DateUtils.formatDateTime(App.getAppContext(), date).toString();
    }


    public static List<NewsDisplayableModel> convert(List<NewsItem> newsItem) {
        List<NewsDisplayableModel> displayableModels = new ArrayList<>();
        for (NewsItem item : newsItem) {

            displayableModels.add(new NewsDisplayableModel(item.getTitle(),
                    item.getImageUrl(),
                    item.getNewsCategory(),
                    getNewsDate(item.getPublishDate()),
                    item.getPreviewText(),
                    item.getFullText()));
        }

        return displayableModels;
    }
}
