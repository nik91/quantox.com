package com.karovic.nikola.themovieapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.karovic.nikola.themovieapp.R;
import com.karovic.nikola.themovieapp.ui.adapter.MovieListAdapter;
import com.karovic.nikola.themovieapp.viewmodel.TopRatedMoviesViewModel;

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

        topRatedMoviesRecyclerView = view.findViewById(R.id.top_rated_movies_recycler_view);
        topRatedMoviesEmptyTV = view.findViewById(R.id.top_rated_list_empty);
        topRatedMoviesProgressBar = view.findViewById(R.id.top_rated_list_progress_bar);

        return view;
    }
}
