package com.kotlinMaster.movie_app.dataholder

import com.kotlinMaster.movie_app.R

class ActorsDataSource {
    fun getActors(): List<Actor> {
        return mutableListOf(
            Actor(R.drawable.first_actor_avengers, "Robert Downey Jr."),
            Actor(R.drawable.second_actor_avengers, "Chris Evans"),
            Actor(R.drawable.third_actor_avengers, "Mark Ruffalo"),
            Actor(R.drawable.forth_actor_avengers, "Chris Hemsworth")
        )
    }
}