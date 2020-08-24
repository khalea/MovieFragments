package com.example.fragments;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieAPI {
    // Add Query String Between These
    private static String baseURL = "https://api.themoviedb.org/3/search/movie?api_key=" + Keys.getTmdbKey() + "&query=";
    private static String endURL = "&language=en-US&page=1&include_adult=false&page=";

    private static String trendURL = "https://api.themoviedb.org/3/trending/all/week?api_key=" + Keys.getTmdbKey();

    private static String currentQueryString; // Query string without page -> helps with pagination

    private static int currentPage;
    private static JSONArray resultsArray;

    // Constructor
    public MovieAPI() {
    }


    public void loadTrending(final Context context) {
        MovieContent.clearItems();

        // Start Request
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, trendURL,
                response -> {
                    try {
                        // Add results to array
                        resultsArray = new JSONObject(response).getJSONArray("results");
                        // Add movies to MovieContent.ITEMS
                        for (int i = 0; i < resultsArray.length(); i++) {

                            JSONObject movie = resultsArray.getJSONObject(i);


                            if (movie.has("title")) {
                                Log.d("API", movie.get("title").toString());
                                // Movie Details
                                String movieTitle = movie.get("title").toString();
                                String movieOverview = movie.get("overview").toString();
                                String movieRelease = movie.get("release_date").toString();
                                float movieRating = (float) Float.parseFloat(movie.get("vote_average").toString());

                                // Poster & Backdrop
                                String moviePoster = "https://image.tmdb.org/t/p/w500" + movie.get("poster_path").toString();

                                // Add Movie to MovieContent.ITEMS
                                MovieContent.addItem(new MovieContent.Movie(movieTitle, movieRelease, movieOverview, moviePoster, movieRating));

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    Log.d("API", "Error: " + error.toString());
                });

        requestQueue.add(stringRequest);
    }

    public void find(String query, final Context context, int page) {
        // Start RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);
        String queryURL = baseURL + query + endURL + page;

        MovieAPI.currentPage = page;
        MovieAPI.currentQueryString = baseURL + query + endURL;

        // Don't clear list if page > 1
        if (page == 1) {
            MovieContent.clearItems();
        }

        // Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, queryURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("API", "Response: " + response.substring(0, response.length() / 2));

                try {
                    // Make a JSON obj, send results to MovieContent.ITEMS
                    resultsArray = new JSONObject(response).getJSONArray("results");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject movie = resultsArray.getJSONObject(i);

                        // Only add if there is a poster & description
                        // TODO doesn't eliminate results properly
                        if ((!(movie.get("overview") == null)) || (!(movie.get("poster_path") == null))) {

                            Log.d("API", movie.get("title").toString());

                            // Movie Details
                            String movieTitle = movie.get("title").toString();
                            String movieOverview = movie.get("overview").toString();
                            String movieRelease = movie.get("release_date").toString();
                            float movieRating = (float) Float.parseFloat(movie.get("vote_average").toString());

                            // Poster & Backdrop
                            String moviePoster = "https://image.tmdb.org/t/p/w500" + movie.get("poster_path").toString();

                            // Add Movie to MovieContent.ITEMS
                            MovieContent.addItem(new MovieContent.Movie(movieTitle, movieRelease, movieOverview, moviePoster, movieRating));
                        }
                    }

                } catch (JSONException jsonError) {
                    Log.d("API", "JSON Error: " + jsonError.toString());
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("API", "Error: " + error.toString());
                    }
                }
        );
        queue.add(stringRequest);
    }

    public static String getCurrentQuery() {
        return MovieAPI.currentQueryString;
    }

    // Helps with pagination
    public static void setCurrentPage(int nextPage) {
        MovieAPI.currentPage = nextPage;
    }

    public static int getCurrentPage() {
        return currentPage;
    }
}
