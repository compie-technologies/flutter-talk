package com.example.fluttertalkandroid.models;

import com.google.gson.annotations.SerializedName;

public class GenreModel {
    @SerializedName("id")
    final int id;

    @SerializedName("name")
    final String name;

    public GenreModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
