package com.example.movieskotlin.viewmodels

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movieskotlin.R

import com.example.movieskotlin.models.Movie
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 * TODO: Replace the implementation with code for your data type.
 */
class FeaturedMovieRVAdapter(
    private val values: List<Movie>
) : RecyclerView.Adapter<FeaturedMovieRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_featured, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = values[position]
        holder.titleView.text = movie.title
        holder.overviewView.text = movie.overview
        holder.ratingView.numStars = 5
        holder.ratingView.rating = movie.rating/2
        Picasso.get().load(movie.posterURL).into(holder.posterView)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.FeaturedTitle)
        val overviewView: TextView = view.findViewById(R.id.FeaturedOverview)
        val ratingView: RatingBar = view.findViewById(R.id.FeaturedRating)
        val posterView: ImageView = view.findViewById(R.id.FeaturedPoster)

        override fun toString(): String {
            return super.toString() + " '" + overviewView.text + "'"
        }
    }
}