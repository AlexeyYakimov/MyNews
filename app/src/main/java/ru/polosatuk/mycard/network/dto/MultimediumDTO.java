package ru.polosatuk.mycard.network.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultimediumDTO {

    @Nullable
    @SerializedName("url")
    private String url;

    @Nullable
    @SerializedName("format")
    private String format;

    @Nullable
    @SerializedName("height")
    private Integer height;

    @Nullable
    @SerializedName("width")
    private Integer width;

    @Nullable
    @SerializedName("type")
    private String type;

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getFormat() {
        return format;
    }

    @Nullable
    public Integer getHeight() {
        return height;
    }

    @Nullable
    public Integer getWidth() {
        return width;
    }

    @Nullable
    public String getType() {
        return type;
    }


}
