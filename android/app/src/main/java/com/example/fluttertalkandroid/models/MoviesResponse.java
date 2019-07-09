package com.example.fluttertalkandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesResponse {
    @SerializedName("page")
    private final int page;

    @SerializedName("results")
    private final ArrayList<MovieModel> results;

    @SerializedName("total_results")
    private final int totalResults;

    @SerializedName("total_pages")
    private final int totalPages;

    public MoviesResponse(int page, ArrayList<MovieModel> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
