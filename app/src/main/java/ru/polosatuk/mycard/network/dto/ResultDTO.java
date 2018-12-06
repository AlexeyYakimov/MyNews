package ru.polosatuk.mycard.network.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDTO {

    @Nullable
    @SerializedName("title")
    @Expose
    private String title;

    @Nullable
    @SerializedName("abstract")
    @Expose
    private String _abstract;

    @Nullable
    @SerializedName("url")
    @Expose
    private String url;

    @Nullable
    @SerializedName("section")
    @Expose
    private String section;

    @Nullable
    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    @Nullable
    @SerializedName("multimedia")
    @Expose
    private List<MultimediumDTO> multimedia = null;

    @Nullable
    @SerializedName("short_url")
    @Expose
    private String shortUrl;

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getSection() {return section;}

    @Nullable
    public String getAbstract() {
        return _abstract;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getPublishedDate() {
        return publishedDate;
    }

    @Nullable
    public List<MultimediumDTO> getMultimedia() {
        return multimedia;
    }

    @Nullable
    public String getShortUrl() {
        return shortUrl;
    }
}
