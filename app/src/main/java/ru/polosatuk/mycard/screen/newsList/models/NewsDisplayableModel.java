package ru.polosatuk.mycard.screen.newsList.models;


import android.support.annotation.NonNull;

import java.io.Serializable;


public class NewsDisplayableModel implements Serializable {
    @NonNull
    private final String title;
    @NonNull
    private final String imageUrl;
    @NonNull
    private final NewsCategory newsCategory;
    @NonNull
    private final String publishDate;
    @NonNull
    private final String previewText;
    @NonNull
    private final String fullText;
    @NonNull
    private final String bigImageUrl;

    public NewsDisplayableModel(@NonNull String title,
                                @NonNull String imageUrl,
                                @NonNull NewsCategory newsCategory,
                                @NonNull String publishDate,
                                @NonNull String previewText,
                                @NonNull String fullText,
                                @NonNull String bigImageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.newsCategory = newsCategory;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
        this.bigImageUrl = bigImageUrl;
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
    public NewsCategory getNewsCategory() {
        return newsCategory;
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
    public String getFullText() {
        return fullText;
    }

    @NonNull
    public String getBigImageUrl() {
        return bigImageUrl;
    }


}
