package com.example.fluttertalkandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class MovieModel {
    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("adult")
    private final Boolean adult;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("release_date")
    private final Date releaseDate;

    @SerializedName("genre_ids")
    private final ArrayList<Integer> genreIds;

    @SerializedName("original_title")
    private final String originalTitle;

    @SerializedName("original_langauge")
    private final String originalLanguage;

    @SerializedName("title")
    private final String title;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("popularity")
    private final double popularity;

    @SerializedName("vote_count")
    private final int voteCount;

    @SerializedName("video")
    private final Boolean video;

    @SerializedName("vote_average")
    private final double voteAverage;

    public MovieModel(String posterPath, Boolean adult, String overview, Date releaseDate, ArrayList<Integer> genreIds, String originalTitle, String originalLanguage, String title, String backdropPath, double popularity, int voteCount, Boolean video, double voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;

        this.genreIds = genreIds;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }
}
