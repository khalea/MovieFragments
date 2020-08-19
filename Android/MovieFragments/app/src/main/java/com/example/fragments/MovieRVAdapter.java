package com.example.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragments.MovieContent.Movie;

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
        public final TextView businessNameView;
        public final TextView businessBioView;
        public Movie mItem;

        private final ViewHolderListener viewHolderListener;

        // ViewHolder constructor
        public ViewHolder(View view, ViewHolderListener viewHolderListener) {
            super(view);
            mView = view;
            // Locate textviews for items in the List
            businessNameView = (TextView) view.findViewById(R.id.item_name);
            businessBioView = (TextView) view.findViewById(R.id.item_bio);
            // Set/bind viewholderlistener
            this.viewHolderListener = viewHolderListener;
            itemView.findViewById(R.id.item_row).setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + businessBioView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            viewHolderListener.onItemClicked(view, getAdapterPosition());
        }
    }

    private static class ViewHolderListenerImpl implements ViewHolderListener {

        private Fragment fragment;
        private List<Movie> businesses;

        public ViewHolderListenerImpl(Fragment fragment, List<Movie> businesses) {
            this.fragment = fragment;
            this.businesses = businesses;
        }

        // Handles clicks on rows by setting current posn in MainActivty to given
        // @param view is the clicked row
        // @param adapter position is the selected row
        @Override
        public void onItemClicked(View view, int adapterPosition) {
            // Update position in MainActivity
            MainActivity.currentPosn = adapterPosition;
            MainActivity.currentBusiness = businesses.get(adapterPosition);
            // On Click, open a new DetailView for the given business
            // TODO pass data into new detail view

            DetailFragment detail = new DetailFragment();
            Bundle args = new Bundle();
            args.putString("Business", String.valueOf(businesses.get(MainActivity.currentPosn)));
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
        holder.businessNameView.setText(mValues.get(position).getMovieTitle());
        holder.businessBioView.setText(mValues.get(position).getMovieOverview());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}