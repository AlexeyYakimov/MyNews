package ru.polosatuk.mycard.screen.newsList.models;


import android.support.annotation.NonNull;

import java.io.Serializable;


public class NewsDisplayableModel implements Serializable {
    @NonNull
    private final String title;
    @NonNull
    private final String imageUrl;
    @NonNull
    private final String newsSubCategory;
    @NonNull
    private final String publishDate;
    @NonNull
    private final NewsCategory newsCategory;
    @NonNull
    private final String previewText;
    @NonNull
    private final String url;

    @NonNull
    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public NewsDisplayableModel(@NonNull String title,
                                @NonNull String imageUrl,
                                @NonNull String newsSubCategory,
                                @NonNull NewsCategory newsCategory,
                                @NonNull String publishDate,
                                @NonNull String previewText,
                                @NonNull String url) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.newsSubCategory = newsSubCategory;
        this.newsCategory = newsCategory;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.url = url;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public String getNewsSubCategory() {
        return newsSubCategory;
    }

    @NonNull
    public String getPublishDate() {
        return publishDate;
    }

    @NonNull
    public String getPreviewText() {
        return previewText;
    }

    @NonNull
    public String getUrl() {
        return url;
    }


}
