package com.example.fluttertalkandroid;

import com.example.fluttertalkandroid.models.MoviesResponse;

public interface GetMoviesListener {
    void onSuccess(MoviesResponse response);
}
