package ru.polosatuk.mycard.news.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import androidx.annotation.NonNull;

public class NewsItem {

    private final String title;
    private final String imageUrl;
    private final NewsCategory newsCategory;
    private final Date publishDate;
    private final String previewText;
    private final String fullText;

    public NewsItem(String title, String imageUrl, NewsCategory newsCategory, Date publishDate, String previewText, String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.newsCategory = newsCategory;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public Date getPublishDate() {
        return publishDate;

    }

    public String getPreviewText() {
        return previewText;
    }

    public String getFullText() {
        return fullText;
    }

    public static class NewsCategory {
        private final int id;
        private final String name;

        public NewsCategory(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @NonNull
    public String newsToJson(NewsItem newsItem) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Log.d("Tag", gson.toJson(newsItem));
        return gson.toJson(newsItem);
    }


}

