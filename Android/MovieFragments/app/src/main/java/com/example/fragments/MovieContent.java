package com.example.fragments;

import android.content.res.Resources;
import android.media.Image;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;

public class MovieContent {

    public static final List<Movie> ITEMS = new ArrayList<>();

    static {
        ITEMS.add(new Movie("The Double",
                "2013",
                "An awkward office drone (Jesse Eisenberg) becomes increasingly unhinged after a charismatic and confident look-alike takes a job at his workplace and seduces the woman (Mia Wasikowska) he desires.")
        );

        ITEMS.add(new Movie("The Double",
                "2013",
                "An awkward office drone (Jesse Eisenberg) becomes increasingly unhinged after a charismatic and confident look-alike takes a job at his workplace and seduces the woman (Mia Wasikowska) he desires.")
        );
    }

    // TODO figure out how to load in images

    public static class Movie {
        private String movieTitle;
        private String movieRelease;
        private String movieOverview;
        // private Image moviePoster;

        // TODO add image back to constructor
        public Movie(String movieTitle, String movieRelease, String movieOverview) {
            this.movieTitle = movieTitle;
            this.movieRelease = movieRelease;
            this.movieOverview = movieOverview;
            // this.moviePoster = moviePoster;
        }

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

        /* public Image getMoviePoster() {
            return moviePoster;
        }

        public void setMoviePoster(Image moviePoster) {
            this.moviePoster = moviePoster;
        }
         */
    }

}
