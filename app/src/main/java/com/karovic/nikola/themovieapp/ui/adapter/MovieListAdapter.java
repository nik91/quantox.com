package com.karovic.nikola.themovieapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.karovic.nikola.themovieapp.BuildConfig;
import com.karovic.nikola.themovieapp.R;
import com.karovic.nikola.themovieapp.model.Movie;

public class MovieListAdapter extends ListAdapter<Movie, MovieListAdapter.MovieListViewHolder> {

    private OnItemClickListener listener;
    Context mContext;

    public MovieListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder movieListViewHolder, int i) {
        Movie movie = getItem(i);


        movieListViewHolder.movieTitle.setText(movie.getTitle());

        movieListViewHolder.movieReleaseDate.setText(movie.getReleaseDate());

        movieListViewHolder.movieDescription.setText(movie.getOverview());

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.img_blank)
                .error(R.drawable.img_blank);

        Glide.with(mContext)
                .load(BuildConfig.IMAGES_URL+ movie.getPosterPath())
                .apply(options)
                .into(movieListViewHolder.movieImage);
    }


    class MovieListViewHolder extends RecyclerView.ViewHolder {


        ImageView movieImage;
        TextView movieTitle;
        TextView movieReleaseDate;
        TextView movieDescription;
        ImageView favoriteImageView;
        RelativeLayout movieItemRelativeLayout;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieReleaseDate = itemView.findViewById(R.id.movie_date);
            movieDescription = itemView.findViewById(R.id.movie_details);
            favoriteImageView = itemView.findViewById(R.id.movie_added_to_favorite);
            movieItemRelativeLayout = itemView.findViewById(R.id.movie_item_relative_layout);

            movieItemRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position), position);
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie item, int position);

    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
