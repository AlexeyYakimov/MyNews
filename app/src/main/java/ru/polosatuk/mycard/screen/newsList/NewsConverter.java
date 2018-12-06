package ru.polosatuk.mycard.screen.newsList;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.polosatuk.mycard.App;
import ru.polosatuk.mycard.network.dto.MultimediumDTO;
import ru.polosatuk.mycard.network.dto.NewsDTO;
import ru.polosatuk.mycard.network.dto.ResultDTO;
import ru.polosatuk.mycard.screen.newsList.models.NewsCategory;
import ru.polosatuk.mycard.screen.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.screen.utils.DateUtils;

public class NewsConverter {
    private static final String LOG_TAG = "NewsConverter";

    private NewsConverter() {
        throw new IllegalAccessError("Not exist");
    }

    @Nullable
    public static List<NewsDisplayableModel> convert(NewsDTO newsItem) {
        List<NewsDisplayableModel> displayableModels = new ArrayList<>();
        DateUtils utils = new DateUtils(App.getAppContext());

        for (ResultDTO item : newsItem.getResults()) {
            displayableModels.add(new NewsDisplayableModel(item.getTitle(),
                    getPreviewImage(item.getMultimedia()),
                    item.getSection(),
                    choseSection(newsItem.getSection()),
                    utils.formatDateTime(item.getPublishedDate()).toString(),
                    item.getAbstract(),
                    item.getUrl()));
        }

        return displayableModels;
    }

    @Nullable
    private static String getPreviewImage(List<MultimediumDTO> multimedia) {
        if (multimedia.size() >= 2) {
            return multimedia.get(1).getUrl();
        } else if (multimedia.size() >= 1) {
            return multimedia.get(0).getUrl();
        } else {
            Log.d(LOG_TAG, "No image in api");
            return "";
        }
    }

    @NonNull
    private static NewsCategory choseSection(String section) {
        return NewsCategory.valueOf(section);
    }
}
