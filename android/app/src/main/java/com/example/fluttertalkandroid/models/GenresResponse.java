package com.example.fluttertalkandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GenresResponse {
    @SerializedName("genres")
    private final ArrayList<GenreModel> genres;

    public GenresResponse(ArrayList<GenreModel> genres) {
        this.genres = genres;
    }

    public ArrayList<GenreModel> getGenres() {
        return genres;
    }
}
