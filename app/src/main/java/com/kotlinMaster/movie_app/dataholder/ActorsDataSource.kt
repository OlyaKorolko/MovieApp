package com.kotlinMaster.movie_app.dataholder

import com.kotlinMaster.movie_app.R

class ActorsDataSource {
    fun getActors(): List<Actor> {
        return mutableListOf(
            Actor(R.drawable.first_actor, "Robert Downey Jr."),
            Actor(R.drawable.second_actor, "Chris Evans"),
            Actor(R.drawable.third_actor, "Mark Ruffalo"),
            Actor(R.drawable.forth_actor, "Chris Hemsworth")
        )
    }
}