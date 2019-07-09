package com.example.fluttertalkandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

public class MovieItem extends ConstraintLayout {

    private TextView title;
    private TextView genres;

    private ImageView poster;

    public MovieItem(Context context) {
        super(context);
        init(context);
    }

    public MovieItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovieItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.movie_item, this);

        title = findViewById(R.id.tv_title);
        genres = findViewById(R.id.tv_genres);

        poster = findViewById(R.id.iv_poster);
    }

    public void setTitle(String titleText) {
        if (title != null) {
            title.setText(titleText);
        }
    }

    public void setGenres(String genresText) {
        if (genres != null) {
            genres.setText(genresText);
        }
    }

    public void setPoster(String imageUrl) {
        if (poster != null) {
            Glide.with(this).load(imageUrl).placeholder(R.drawable.placeholder).into(poster);
        }
    }
}
