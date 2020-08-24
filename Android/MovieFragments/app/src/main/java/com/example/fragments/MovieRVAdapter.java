package com.example.fragments;

import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothClass;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.fragments.MovieContent.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieRVAdapter extends RecyclerView.Adapter<MovieRVAdapter.ViewHolder> {

    private final List<Movie> mValues;
    private final ViewHolderListener viewHolderListener;

    // Listener attached to all Viewholders (rows) that handles clicks
    private interface ViewHolderListener {
        void onItemClicked(View view, int adapterPosition);
    }

    // ViewHolder describes list item view and metadata about its place in the RecyclerView
    // RVAdapters should implement ViewHolder & add fields to cache View.findviewbyid results
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public Movie mItem;

        public final TextView movieTitleView;
        public final TextView movieOverviewView;
        public final ImageView moviePosterView;
        public final RatingBar ratingBar;


        private final ViewHolderListener viewHolderListener;

        // ViewHolder constructor
        public ViewHolder(View view, ViewHolderListener viewHolderListener) {
            super(view);
            mView = view;
            // Locate textviews for items in the List
            movieTitleView = (TextView) view.findViewById(R.id.item_name);
            movieOverviewView = (TextView) view.findViewById(R.id.item_bio);
            moviePosterView = (ImageView) view.findViewById(R.id.imageView);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            // Set/bind viewholderlistener
            this.viewHolderListener = viewHolderListener;
            itemView.findViewById(R.id.item_row).setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + movieOverviewView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            viewHolderListener.onItemClicked(view, getAdapterPosition());
        }
    }

    private static class ViewHolderListenerImpl implements ViewHolderListener {

        private Fragment fragment;
        private List<Movie> movies;

        public ViewHolderListenerImpl(Fragment fragment, List<Movie> movies) {
            this.fragment = fragment;
            this.movies = movies;
        }

        // Handles clicks on rows by setting current posn in MainActivty to given
        // @param view is the clicked row
        // @param adapter position is the selected row
        @Override
        public void onItemClicked(View view, int adapterPosition) {
            // Update position in MainActivity
            MainActivity.currentPosn = adapterPosition;
            MainActivity.currentMovie = movies.get(adapterPosition);
            // On Click, open a new DetailView for the given Movie
            // TODO pass data into new detail view

            DetailFragment detail = new DetailFragment();
            Bundle args = new Bundle();
            args.putString("Movie", String.valueOf(movies.get(MainActivity.currentPosn)));
            detail.setArguments(args);

            assert fragment.getFragmentManager() != null;
            fragment.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainActivityContainer, detail, "DetailFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    // Builds adapter for given data and fragment
    public MovieRVAdapter(List<Movie> items, Fragment fragment) {
        mValues = items;
        this.viewHolderListener = new ViewHolderListenerImpl(fragment, items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creates new fragment_item row
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view, viewHolderListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Bind data to row/viewholder
        // Set text for items in the List
        holder.mItem = mValues.get(position);
        holder.movieTitleView.setText(mValues.get(position).getMovieTitle());
        holder.movieOverviewView.setText(mValues.get(position).getMovieOverview());
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setRating((float) mValues.get(position).getMovieRating()/2);

        // TODO access movie poster list view

        if (!(mValues.get(position).getMoviePoster().equals("https://image.tmdb.org/t/p/w500null"))) {
            Picasso.get()
                    .load(mValues.get(position).getMoviePoster())
                    .into(holder.moviePosterView);
        } else { // TODO fix default image not showing up (or showing up too large without fit, centercrop)
            Picasso.get()
                    .load(R.drawable.movie)
                    .fit()
                    .centerCrop()
                    .into(holder.moviePosterView);
        }
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

}