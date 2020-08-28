package com.example.movieskotlin.models

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movieskotlin.models.MovieContent
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.example.movieskotlin.models.Keys as Keys

class MovieAPI {
    // Necessary URLs for movie search & featured movies
    val baseURL : String = "https://api.themoviedb.org/3/search/movie?api_key=" + Keys.getTMBDKey() + "&query="
    val endURL : String = "&language=en-US&page=1&include_adult=false&page="
    val featuredURL : String = "https://api.themoviedb.org/3/trending/all/week?api_key=" + com.example.movieskotlin.models.Keys.getTMBDKey()

    // Current Values -> Helps with loading new pages
    var currentQueryString : String
        get() {
            return currentQueryString
        }
        set(value) {
            currentQueryString = value
        }
    var currentPage : Int
        get() {
            return currentPage
        }
        set(value) {
            currentPage = value
        }

    // TODO Search

    // Load Trending
    fun loadFeatured(context: Context) {
        // Clear Items
        // MovieContent.clear()

        Log.d("API", "Req: $featuredURL")

        // Start Request
        val queue : RequestQueue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, featuredURL,
            Response.Listener<String> {
                response ->
                try {
                    // Store results as JSONArray
                    var resultsArray : JSONArray = JSONObject(response).getJSONArray("results")
                    // Add movies to MovieContent.ITEMS
                    for (i in 0 until resultsArray.length()) {
                        val movie : JSONObject = resultsArray.getJSONObject(i)
                        if (movie.has("title")) {
                            val id : Int = movie.get("id") as Int
                            val title : String = movie.get("title").toString()
                            val overview : String = movie.get("overview").toString()
                            val release : String = movie.get("release_date").toString()
                            val poster : String = "https://image.tmdb.org/t/p/w500" + movie.get("poster_path").toString()
                            val rating : Float = movie.get("vote_average").toString().toFloat()

                            Log.d("API", "Movie: $title")
                            MovieContent.addMovie(Movie(id, title, overview, release, rating, poster))
                        }
                    }
                } catch (error : JSONException) {
                    error.printStackTrace()
                }
            },
            Response.ErrorListener {
                Log.d("API", "Featured movies did not load.")
            })

        // Add to RequestQueue
        queue.add(stringRequest)
    }

}