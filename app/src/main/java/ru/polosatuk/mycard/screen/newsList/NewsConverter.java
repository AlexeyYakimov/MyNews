package ru.polosatuk.mycard.screen.newsList;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import ru.polosatuk.mycard.network.dto.MultimediumDTO;
import ru.polosatuk.mycard.network.dto.NewsDTO;
import ru.polosatuk.mycard.network.dto.ResultDTO;
import ru.polosatuk.mycard.screen.newsList.models.NewsCategory;
import ru.polosatuk.mycard.screen.newsList.models.NewsDisplayableModel;

public class NewsConverter {
    private static final String TAG = "NewsConverter";

    private NewsConverter() {
        throw new IllegalAccessError("Not exist");
    }

    public static List<NewsDisplayableModel> convert(NewsDTO newsItem) {
        List<NewsDisplayableModel> displayableModels = new ArrayList<>();
        String section = newsItem.getSection().toUpperCase();


        for (ResultDTO item : newsItem.getResults()) {
            displayableModels.add(new NewsDisplayableModel(item.getTitle(),
                    getPreviewImage(item.getMultimedia()),
                    choseSection(section),
                    item.getPublishedDate(),
                    item.getAbstract(),
                    item.getAbstract(),
                    getBigImageUrl(item.getMultimedia())));
        }

        return displayableModels;
    }

    @NonNull
    private static String getPreviewImage(List<MultimediumDTO> multimedia) {
        if (multimedia.size() >= 2) {
            return multimedia.get(1).getUrl();
        } else if (multimedia.size() >= 1) {
            return multimedia.get(0).getUrl();
        } else {
            Log.d(TAG, "No image in api");
            return "";
        }
    }

    private static String getBigImageUrl(List<MultimediumDTO> multimedia) {
        if (!multimedia.isEmpty()) {
            return multimedia.get(multimedia.size() - 1).getUrl();
        } else {
            return "";
        }
    }

    private static NewsCategory choseSection(String section) {
        return NewsCategory.valueOf(section);
    }
}
