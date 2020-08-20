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
    private static String endURL = "&language=en-US&page=1&include_adult=false";

    // Constructor
    public MovieAPI() {
    }

    // Assemble Query URL
    // Send Request
    // Parse Results (JSON -> MovieContent.Movie)
    // Add to MovieContent.ITEMS


    public void find(String query, final Context context) {
        // Start RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);
        String queryURL = baseURL + query + endURL;

        // Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, queryURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("API", "Response: " + response.substring(0, response.length()/2));

                MovieContent.clearItems();

                try {
                    // Make a JSON obj, send results to MovieContent.ITEMS
                    JSONObject resultsObject = new JSONObject(response);
                    JSONArray resultsArray = resultsObject.getJSONArray("results");
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject movie = resultsArray.getJSONObject(i);

                        Log.d("API", movie.get("title").toString());

                        // String movieID = movie.get("id").toString();
                        String movieTitle = movie.get("title").toString();
                        String movieOverview = movie.get("overview").toString();
                        String movieRelease = movie.get("release_date").toString();

                        // TODO Movie Poster
                        // String moviePosterURL = movie.get("poster_path").toString();

                        MovieContent.addItem(new MovieContent.Movie(movieTitle, movieRelease, movieOverview));
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


}
