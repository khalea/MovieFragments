package com.example.movieskotlin.models

data class Movie constructor(var id : Int, var title : String,
                        var overview : String, var releaseDate : String,
                        var rating : Float, var posterURL : String
                        ) {}