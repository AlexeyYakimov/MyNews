package ru.polosatuk.mycard.news.data;

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
    public Date getPublishDate() {
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

