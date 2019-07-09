package com.example.fluttertalkandroid;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.fluttertalkandroid.models.MoviesResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetMovies extends AsyncTask<String, Void, Boolean> {

    private static final String ROUTE = "/discover/movie";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private GetMoviesListener listener;

    private int page;

    public GetMovies(GetMoviesListener listener, int page) {
        this.listener = listener;
        this.page = page;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("api_key", Constants.apiKey);

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, -9);
            Date gte = new Date(c.getTimeInMillis());
            params.put("primary_release_date.gte", dateFormat.format(gte));

            c.setTime(new Date());
            c.add(Calendar.DATE, 9);
            Date lte = new Date(c.getTimeInMillis());
            params.put("primary_release_date.lte", dateFormat.format(lte));
            params.put("page", String.valueOf(page));

            URL url = new URL(Constants.baseUrl + Helpers.attachParams(ROUTE, params));
            urlConnection = (HttpURLConnection) url.openConnection();
            Reader r;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                r = new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8);
            } else {
                r = new InputStreamReader(urlConnection.getInputStream());
            }

            Gson gson = new Gson();

            final MoviesResponse moviesResponse = gson.fromJson(r, MoviesResponse.class);

            if (this.listener != null) {
                new Handler(Looper.getMainLooper()).post(() -> this.listener.onSuccess(moviesResponse));
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
