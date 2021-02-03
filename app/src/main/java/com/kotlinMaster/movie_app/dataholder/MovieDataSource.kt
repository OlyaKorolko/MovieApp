package com.kotlinMaster.movie_app.dataholder

import com.kotlinMaster.movie_app.R

class MovieDataSource {
    fun getMovies(): List<Movie> {
        return mutableListOf(
            Movie(R.drawable.avengers_poster, R.drawable.age_13, false, "Action, Adventure, Drama", 4,
            125, "Avengers: End Game", 137),
            Movie(R.drawable.tenet_poster, R.drawable.age_16, true, "Action, Sci-Fi, Thriller", 5,
                98, "Tenet", 97),
            Movie(R.drawable.black_widow_poster, R.drawable.age_13, false, "Action, Adventure, Sci-Fi", 4,
            38, "Black Widow", 102),
            Movie(R.drawable.wonder_woman_poster, R.drawable.age_13, false, "Action, Adventure, Fantasy", 5,
            74, "Wonder Woman 1984", 120)
        )
    }
}