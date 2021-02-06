package com.kotlinMaster.movie_app.dataholder

import com.kotlinMaster.movie_app.R

class MovieDataSource {
    fun getMovies(): List<Movie> {
        return mutableListOf(
            Movie(
                R.drawable.avengers_poster,
                R.drawable.avengers_in_shadow,
                R.drawable.age_13,
                false,
                "Action, Adventure, Drama",
                4,
                125,
                STORYLINE_AVENGERS,
                "Avengers: End Game",
                137,
                mutableListOf(
                    Actor(R.drawable.first_actor_avengers, "Robert Downey Jr."),
                    Actor(R.drawable.second_actor_avengers, "Chris Evans"),
                    Actor(R.drawable.third_actor_avengers, "Mark Ruffalo"),
                    Actor(R.drawable.forth_actor_avengers, "Chris Hemsworth")
                )
            ),
            Movie(
                R.drawable.tenet_poster,
                R.drawable.tenet_in_shadows,
                R.drawable.age_16,
                true,
                "Action, Sci-Fi , Thriller",
                5,
                98,
                STORYLINE_TENET,
                "Tenet",
                97,
                mutableListOf(
                    Actor(R.drawable.juhan, "Juhan Ulfsak"),
                    Actor(R.drawable.jefferson, "Jefferson Hall"),
                    Actor(R.drawable.andrew, "Andrew Howard"),
                    Actor(R.drawable.john, "John David Washington"),
                    Actor(R.drawable.rich, "Rich Ceraulo Ko"),
                    Actor(R.drawable.jonathan, "Jonathan Camp"),
                    Actor(R.drawable.wes, "Wes Chatham")
                )
            ),
            Movie(
                R.drawable.black_widow_poster,
                R.drawable.avengers_in_shadow,
                R.drawable.age_13,
                false,
                "Action, Adventure, Sci-Fi",
                4,
                38,
                STORYLINE_AVENGERS,
                "Black Widow",
                102,
                mutableListOf(
                    Actor(R.drawable.first_actor_avengers, "Robert Downey Jr."),
                    Actor(R.drawable.second_actor_avengers, "Chris Evans"),
                    Actor(R.drawable.third_actor_avengers, "Mark Ruffalo"),
                    Actor(R.drawable.forth_actor_avengers, "Chris Hemsworth")
                )
            ),
            Movie(
                R.drawable.wonder_woman_poster,
                R.drawable.tenet_in_shadows,
                R.drawable.age_13,
                false,
                "Action, Adventure, Fantasy",
                5,
                74,
                STORYLINE_TENET,
                "Wonder Woman 1984",
                120,
                mutableListOf(
                    Actor(R.drawable.juhan, "Juhan Ulfsak"),
                    Actor(R.drawable.jefferson, "Jefferson Hall"),
                    Actor(R.drawable.andrew, "Andrew Howard"),
                    Actor(R.drawable.john, "John David Washington"),
                    Actor(R.drawable.rich, "Rich Ceraulo Ko"),
                    Actor(R.drawable.jonathan, "Jonathan Camp"),
                    Actor(R.drawable.wes, "Wes Chatham")
                )
            )
        )
    }

    companion object {
        const val STORYLINE_AVENGERS =
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. " +
                    "With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\'" +
                    " actions and restore balance to the universe."
        const val STORYLINE_TENET =
            "Armed with only one word, Tenet, and fighting for the survival of the entire world, " +
                    "a Protagonist journeys through a twilight world of international espionage on a " +
                    "mission that will unfold in something beyond real time."
    }
}