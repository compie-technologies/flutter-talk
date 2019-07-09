package com.example.fluttertalkandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.fluttertalkandroid.models.GenresResponse;
import com.example.fluttertalkandroid.models.MoviesResponse;

public class MainActivity extends AppCompatActivity implements GetMoviesListener, GetGenresListener {

    private RecyclerView moviesGrid;

    private MoviesGridAdapter adapter;

    private SwipeRefreshLayout refreshLayout;

    private int nextPage = 1;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 4;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title);

        moviesGrid = findViewById(R.id.rv_movies_grid);

        GridLayoutManager gl = new GridLayoutManager(this, 2);

        moviesGrid.setLayoutManager(gl);

        adapter = new MoviesGridAdapter();

        moviesGrid.setAdapter(adapter);

        refreshLayout = findViewById(R.id.srl_main);

        refreshLayout.setOnRefreshListener(() -> fetchMovies(1));


        moviesGrid.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = moviesGrid.getChildCount();
                totalItemCount = gl.getItemCount();
                firstVisibleItem = gl.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    fetchMovies(nextPage);

                    // Do something

                    loading = true;
                }
            }
        });

        fetchGenres();
        fetchMovies(1);
    }

    private void fetchMovies(int page) {
        new GetMovies(this, page).execute();
    }

    private void fetchGenres() {
        new GetGenres(this).execute();
    }

    @Override
    public void onSuccess(GenresResponse response) {
        adapter.updateGenres(response.getGenres());
    }

    @Override
    public void onSuccess(MoviesResponse response) {
        adapter.updateItems(response.getResults(), response.getPage() == 1);
        nextPage = response.getPage() < response.getTotalPages() ? response.getPage() + 1 : -1;
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}
