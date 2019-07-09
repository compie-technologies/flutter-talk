package com.example.fluttertalkandroid;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.fluttertalkandroid.models.GenresResponse;
import com.example.fluttertalkandroid.models.MoviesResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GetGenres extends AsyncTask<String, Void, Boolean> {

    private static final String ROUTE = "/genre/movie/list";

    private GetGenresListener listener;

    public GetGenres(GetGenresListener listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("api_key", Constants.apiKey);

            URL url = new URL(Constants.baseUrl + Helpers.attachParams(ROUTE, params));
            urlConnection = (HttpURLConnection) url.openConnection();
            Reader r;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                r = new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8);
            } else {
                r = new InputStreamReader(urlConnection.getInputStream());
            }

            Gson gson = new Gson();

            final GenresResponse genresResponse = gson.fromJson(r, GenresResponse.class);

            if (this.listener != null) {
                new Handler(Looper.getMainLooper()).post(() -> this.listener.onSuccess(genresResponse));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return false;
    }
}
