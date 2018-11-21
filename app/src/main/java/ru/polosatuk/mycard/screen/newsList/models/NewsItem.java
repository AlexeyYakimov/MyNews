package ru.polosatuk.mycard.screen.newsList.models;

import android.support.annotation.NonNull;

import java.util.Date;



public class NewsItem {
//    Он пока не используется но он очень важен для меня как память.
//    Планирую его запользовать в 6м дз наверное, но если не пригодится удалю

    @NonNull
    private final String title;
    @NonNull
    private final String imageUrl;
    @NonNull
    private final NewsCategory newsCategory;
    @NonNull
    private final Date publishDate;
    @NonNull
    private final String previewText;
    @NonNull
    private final String fullText;

    public NewsItem(@NonNull String title,
                    @NonNull String imageUrl,
                    @NonNull NewsCategory newsCategory,
                    @NonNull Date publishDate,
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

