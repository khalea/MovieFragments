package com.example.fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.fragments.R.id;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    ImageView moviePoster;
    TextView movieTitleView;
    TextView movieOverviewView;
    TextView movieReleaseView;
    RatingBar detailRatingBar;

    MovieContent.Movie biz;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MovieContent.Movie movie = MainActivity.currentMovie;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    // Load data into detailview on start
    @Override
    public void onStart() {
        super.onStart();

        // References for movie attribute views
        this.movieTitleView = getView().findViewById(R.id.movieTitle);
        this.movieOverviewView = getView().findViewById(R.id.movieOverview);
        this.movieReleaseView = getView().findViewById(R.id.movieRelease);
        this.moviePoster = getView().findViewById(id.moviePoster);
        this.detailRatingBar = getView().findViewById(id.detailRatingBar);


        MovieContent.Movie movie = MainActivity.currentMovie;

        // Set values for views
        this.movieTitleView.setText(movie.getMovieTitle());
        this.movieOverviewView.setText(movie.getMovieOverview());
        this.movieReleaseView.setText(movie.getMovieRelease());
        this.detailRatingBar.setNumStars(5);
        this.detailRatingBar.setRating((float) movie.getMovieRating()/2);

        // Check if movie has a poster
        Log.d("Detail", "Poster Path: " + movie.getMoviePoster());

        if (! (movie.getMoviePoster().equals("https://image.tmdb.org/t/p/w500null"))) {

            Picasso.get().load(movie.getMoviePoster()).into(moviePoster);
            // Generate palette with dominant color
            BitmapDrawable bitmapDrawable = (BitmapDrawable) this.moviePoster.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Palette palette = Palette.from(bitmap).generate();
            int dominantColor = palette.getDarkVibrantColor(0);

            Color color = Color.valueOf(dominantColor);
            this.moviePoster.setBackgroundColor(dominantColor);
        }

    }
}