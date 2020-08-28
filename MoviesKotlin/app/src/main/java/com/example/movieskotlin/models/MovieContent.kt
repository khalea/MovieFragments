package com.example.movieskotlin.models
import java.util.ArrayList

object MovieContent {
    // Store Movie objects here
    val ITEMS: MutableList<Movie> = ArrayList()

    init {

    }

    /*
    init {
        ITEMS.add(
            Movie(12345, "Birds of Prey", "Or the Fabulous Emancipation of One Harley Quinn",
                "08-04-2019", 3.5F, "https://image.tmdb.org/t/p/w500/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg"))

        ITEMS.add(Movie(12345, "Birds of Prey", "Or the Fabulous Emancipation of One Harley Quinn",
            "08-04-2019", 3.5F, "https://image.tmdb.org/t/p/w500/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg"))

        ITEMS.add(Movie(12345, "Birds of Prey", "Or the Fabulous Emancipation of One Harley Quinn",
            "08-04-2019", 3.5F, "https://image.tmdb.org/t/p/w500/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg"))
    }*/

    // Add items
    fun addMovie(item: Movie) {
        ITEMS.add(item)
    }

    // Clear Items
    fun clear() {
        ITEMS.clear()
    }

}