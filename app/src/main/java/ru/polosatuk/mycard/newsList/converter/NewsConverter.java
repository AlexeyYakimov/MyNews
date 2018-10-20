package ru.polosatuk.mycard.newsList.converter;

import android.content.Context;
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
import ru.polosatuk.mycard.newsList.data.DataUtils;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.newsList.models.NewsItem;
import ru.polosatuk.mycard.utils.DateUtils;

public class NewsConverter {
   private static Context context;
    @NonNull
    private static GsonBuilder builder = new GsonBuilder();
    @NonNull
    private static Gson gson = builder.create();

    public NewsConverter(Context appContext){
        NewsConverter.context = appContext;
    }

    @NonNull
    public static String newsToJson(NewsDisplayableModel newsItem) {

        return gson.toJson(newsItem);
    }

    @Nullable
    public static NewsDisplayableModel getNewsDetailFromGson(@NonNull String json) {
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
    public static String getNewsDate(Date date) {
        return DateUtils.getDateToNews(date, context);
    }

    public static List<NewsDisplayableModel> convert(List<NewsItem> newsItem) {
        List<NewsDisplayableModel> displayableModels = new ArrayList<>();
        for (int i = 0; i< newsItem.size(); i++){

        displayableModels.add(new NewsDisplayableModel(newsItem.get(i).getTitle(),
                newsItem.get(i).getImageUrl(),
                newsItem.get(i).getNewsCategory().getName(),
                newsItem.get(i).getNewsCategory().getId(),
                getNewsDate(newsItem.get(i).getPublishDate()),
                newsItem.get(i).getPreviewText(),
                newsItem.get(i).getFullText()));
        }

        return displayableModels;
    }
}
