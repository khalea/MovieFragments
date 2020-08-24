package com.example.fragments;

import android.content.res.Resources;
import android.media.Image;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;

public class MovieContent {

    private static final List<Movie> ITEMS = new ArrayList<>();
    private static final List<Movie> FEATURED_ITEMS = new ArrayList<>();

    public static List<Movie> getITEMS() {
        return ITEMS;
    }

    public static List<Movie> getFeaturedItems() { return FEATURED_ITEMS; }

    public static void addItem(Movie movie) {
        ITEMS.add(movie);
    }

    public static void addFeatured(Movie movie) { FEATURED_ITEMS.add(movie); }

    public static void clearItems() {
        ITEMS.clear();
    }

    public static class Movie {
        private String movieTitle;
        private String movieRelease;
        private String movieOverview;
        private String moviePoster = null;
        private float movieRating;

        // TODO add image back to constructor
        public Movie(String movieTitle, String movieRelease, String movieOverview, String moviePoster, float movieRating) {
            this.movieTitle = movieTitle;
            this.movieRelease = movieRelease;
            this.movieOverview = movieOverview;
            this.moviePoster = moviePoster;
            this.movieRating = movieRating;
        }


        // Getters/Setters

        public String getMovieTitle() {
            return movieTitle;
        }

        public void setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        public String getMovieRelease() {
            return movieRelease;
        }

        public void setMovieRelease(String movieRelease) {
            this.movieRelease = movieRelease;
        }

        public String getMovieOverview() {
            return movieOverview;
        }

        public void setMovieOverview(String movieOverview) {
            this.movieOverview = movieOverview;
        }

        public String getMoviePoster() {
            return moviePoster;
        }

        public void setMoviePoster(String moviePoster) {
            this.moviePoster = moviePoster;
        }

        public float getMovieRating() {
            return movieRating;
        }

        public void setMovieRating(float movieRating) {
            this.movieRating = movieRating;
        }
    }

}
