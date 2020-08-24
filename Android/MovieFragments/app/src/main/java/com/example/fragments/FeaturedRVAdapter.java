package com.example.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.fragments.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FeaturedRVAdapter extends RecyclerView.Adapter<FeaturedRVAdapter.ViewHolder> {

    private final List<MovieContent.Movie> mValues;
    private ViewHolderListener viewHolderListener;

    public FeaturedRVAdapter(List<MovieContent.Movie> items, Fragment fragment) {
        mValues = items;
        this.viewHolderListener = new FeaturedVHListenerImpl(items, fragment);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featured_item, parent, false);
        return new ViewHolder(view, viewHolderListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // Bind movie data to list cell/view holder

        holder.mItem = mValues.get(position);
        holder.ftMovieTitleView.setText(mValues.get(position).getMovieTitle());
        holder.ftMovieOverviewView.setText(mValues.get(position).getMovieOverview());
        holder.ftRating.setRating((float) mValues.get(position).getMovieRating()/2);

        // Access movie poster list view
        if (!(mValues.get(position).getMoviePoster().equals("https://image.tmdb.org/t/p/w500null"))) {
            Picasso.get()
                    .load(mValues.get(position).getMoviePoster())
                    .into(holder.ftImageView);
        } else { // TODO fix default image not showing up (or showing up too large without fit, centercrop)
            Picasso.get()
                    .load(R.drawable.movie)
                    .fit()
                    .centerCrop()
                    .into(holder.ftImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private interface ViewHolderListener {
        void onItemClicked(View view, int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View ftMovieView;
        public final TextView ftMovieTitleView;
        public final TextView ftMovieOverviewView;
        public final ImageView ftImageView;
        public final RatingBar ftRating;
        public MovieContent.Movie mItem;

        private final FeaturedRVAdapter.ViewHolderListener viewHolderListener;

        public ViewHolder(View view, ViewHolderListener viewHolderListener) {
            super(view);
            ftMovieView = view;
            ftMovieTitleView = (TextView) view.findViewById(R.id.ftMovieTitle);
            ftMovieOverviewView = (TextView) view.findViewById(R.id.ftMovieOverview);
            ftImageView = (ImageView) view.findViewById(R.id.ftMovieImage);
            ftRating = (RatingBar) view.findViewById(R.id.ftRatingBar);

            this.viewHolderListener = viewHolderListener;
            itemView.findViewById(R.id.featuredItemCard).setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + ftMovieTitleView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            Log.d("Featured", "Onclick occurred");
            viewHolderListener.onItemClicked(view, getAdapterPosition());
        }
    }

    private static class FeaturedVHListenerImpl implements ViewHolderListener {

        private List<MovieContent.Movie> movies;
        private Fragment fragment;

        public FeaturedVHListenerImpl(List<MovieContent.Movie> movies, Fragment fragment)  {
            this.fragment = fragment;
            this.movies = movies;
        }

        @Override
        public void onItemClicked(View view, int adapterPosition) {
            // Update position in MainActivity
            MainActivity.currentPosn = adapterPosition;
            MainActivity.currentMovie = movies.get(adapterPosition);

            // Open new DetailFragment for selected Featured Movie
            DetailFragment detail = new DetailFragment();
            Bundle args = new Bundle();
            args.putString("Movie", String.valueOf(movies.get(adapterPosition)));
            detail.setArguments(args);

            assert fragment.getFragmentManager() != null;
            fragment.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainActivityContainer, detail, "FeaturedDetailFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }
}