
package ru.polosatuk.mycard.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NewsDTO {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<ResultDTO> results = null;

    public String getStatus() {
        return status;
    }


    public String getSection() {
        return section;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public List<ResultDTO> getResults() {
        return results;
    }

}


