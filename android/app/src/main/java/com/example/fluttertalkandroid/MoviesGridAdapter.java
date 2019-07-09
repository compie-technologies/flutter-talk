package com.example.fluttertalkandroid;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fluttertalkandroid.models.GenreModel;
import com.example.fluttertalkandroid.models.MovieModel;

import java.util.ArrayList;

public class MoviesGridAdapter extends RecyclerView.Adapter<MoviesGridAdapter.MoviesGridViewHolder> {

    private ArrayList<MovieModel> mDataSet = new ArrayList<>();

    private ArrayList<GenreModel> mGenres = new ArrayList<>();

    public void updateItems(ArrayList<MovieModel> newItems, Boolean isReplace) {
        if (isReplace) {
            mDataSet = newItems;
        } else {
            mDataSet.addAll(newItems);
        }

        this.notifyDataSetChanged();
    }

    public void updateGenres(ArrayList<GenreModel> genres) {
        mGenres = genres;
    }

    private String getGenreString(ArrayList<Integer> genreIds, int limit) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.min(genreIds.size(), limit); i++) {
            for (GenreModel genre : mGenres) {
                if (genre.getId() == genreIds.get(i)) {
                    if (result.length() != 0) {
                        result.append(", ");
                    }
                    result.append(genre.getName());
                }
            }
        }
        return result.toString();
    }

    @NonNull
    @Override
    public MoviesGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItem v = new MovieItem(parent.getContext());

        return new MoviesGridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesGridViewHolder holder, int position) {
        MovieItem item = (MovieItem)holder.itemView;

        item.setTitle(mDataSet.get(position).getTitle());
        item.setGenres(getGenreString(mDataSet.get(position).getGenreIds(), 2));
        item.setPoster(Constants.baseImageUrl + mDataSet.get(position).getPosterPath());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class MoviesGridViewHolder extends RecyclerView.ViewHolder {

        public MoviesGridViewHolder(@NonNull MovieItem itemView) {
            super(itemView);
        }
    }
}
