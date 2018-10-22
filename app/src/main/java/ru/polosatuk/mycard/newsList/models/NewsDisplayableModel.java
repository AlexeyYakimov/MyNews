package ru.polosatuk.mycard.newsList.models;


import androidx.annotation.NonNull;

public class NewsDisplayableModel {
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

    public NewsDisplayableModel(@NonNull String title,
                                @NonNull String imageUrl,
                                @NonNull NewsCategory newsCategory,
                                @NonNull String publishDate,
                                @NonNull String previewText,
                                @NonNull String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.newsCategory = newsCategory;
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

}
