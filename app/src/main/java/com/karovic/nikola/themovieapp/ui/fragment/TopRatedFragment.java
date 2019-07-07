package com.karovic.nikola.themovieapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karovic.nikola.themovieapp.R;
import com.karovic.nikola.themovieapp.ui.adapter.MovieListAdapter;
import com.karovic.nikola.themovieapp.ui.infinityscroll.EndlessRecyclerViewScrollListener;
import com.karovic.nikola.themovieapp.viewmodel.TopRatedMoviesViewModel;
import com.karovic.nikola.themovieapp.viewmodel.ViewModelFactory;

public class TopRatedFragment extends Fragment {

    //UI Elements
    RecyclerView topRatedMoviesRecyclerView;
    TextView topRatedMoviesEmptyTV;
    ProgressBar topRatedMoviesProgressBar;

    //Adapters
    MovieListAdapter movieListAdapter;

    //ModelViewViewModel
    TopRatedMoviesViewModel topRatedMoviesViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);

        topRatedMoviesViewModel = ViewModelProviders.of(this, new ViewModelFactory(getContext())).get(TopRatedMoviesViewModel.class);

        topRatedMoviesRecyclerView = view.findViewById(R.id.top_rated_movies_recycler_view);
        topRatedMoviesEmptyTV = view.findViewById(R.id.top_rated_list_empty);
        topRatedMoviesProgressBar = view.findViewById(R.id.top_rated_list_progress_bar);

        topRatedMoviesViewModel.getTopRatedMovies("en-US", 1);

        topRatedMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieListAdapter = new MovieListAdapter();
        topRatedMoviesRecyclerView.setAdapter(movieListAdapter);


        showProgressBar();
        loadListOfMovies();
        addInfinityScroll();

        return view;
    }

    private void addInfinityScroll() {

        EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) topRatedMoviesRecyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (totalItemsCount<1000) {
                    topRatedMoviesViewModel.getTopRatedMovies("en-US", page + 1);
                }
            }
        };

        topRatedMoviesRecyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);


    }

    private void loadListOfMovies() {
        topRatedMoviesViewModel.movieMutableLiveData.observe(this, movies -> {
            if (movies != null && !movies.isEmpty()) {
                movieListAdapter.submitList(movies);
            } else {
                topRatedMoviesEmptyTV.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showProgressBar() {
        topRatedMoviesViewModel.state.observe(this, viewModelState -> {
            if (viewModelState != null) {
                switch (topRatedMoviesViewModel.state.getValue()) {
                    case LOADING: {
                        topRatedMoviesProgressBar.setVisibility(View.VISIBLE);
                        break;
                    }
                    case SUCCESS: {
                        topRatedMoviesProgressBar.setVisibility(View.GONE);
                        break;
                    }
                    case FAILURE: {
                        topRatedMoviesProgressBar.setVisibility(View.GONE);
                        topRatedMoviesEmptyTV.setVisibility(View.VISIBLE);
                        break;
                    }
                    default:
                        topRatedMoviesProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
