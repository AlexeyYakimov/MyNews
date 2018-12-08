
package ru.polosatuk.mycard.network.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NewsDTO {
    @Nullable
    @SerializedName("status")
    @Expose
    private String status;

    @Nullable
    @SerializedName("section")
    @Expose
    private String section;

    @Nullable
    @SerializedName("results")
    @Expose
    private List<ResultDTO> results = null;

    @Nullable
    public String getStatus() {
        return status;
    }

    @Nullable
    public String getSection() {
        return section;
    }

    @Nullable
    public List<ResultDTO> getResults() {
        return results;
    }

}


