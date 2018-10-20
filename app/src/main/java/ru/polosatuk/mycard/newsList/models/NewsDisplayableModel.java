package ru.polosatuk.mycard.newsList.models;


import android.content.Context;

import java.util.Date;

import androidx.annotation.NonNull;

public class NewsDisplayableModel {
    @NonNull
    private final String title;
    @NonNull
    private final String imageUrl;
    @NonNull
    private final int newsCategoryId;
    @NonNull
    private final String newsCategoryName;
    @NonNull
    private final String publishDate;
    @NonNull
    private final String previewText;
    @NonNull
    private final String fullText;

    public NewsDisplayableModel(@NonNull String title,
                                @NonNull String imageUrl,
                                @NonNull String newsCategoryName,
                                @NonNull int newsCategoryId,
                                @NonNull String publishDate,
                                @NonNull String previewText,
                                @NonNull String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.newsCategoryId = newsCategoryId;
        this.newsCategoryName = newsCategoryName;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
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
    public String getNewsCategoryName() {
        return newsCategoryName;
    }

    @NonNull
    public int getNewsCategoryId() {
        return newsCategoryId;
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

}
