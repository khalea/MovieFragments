package com.example.movieskotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.movieskotlin.R
import com.example.movieskotlin.models.MovieAPI
import com.example.movieskotlin.models.MovieContent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieAPI : MovieAPI = MovieAPI()
        movieAPI.loadFeatured(this)
    }



    fun loadFeatured() {
        val movieAPI : MovieAPI = MovieAPI()
        movieAPI.loadFeatured(this)
    }

}