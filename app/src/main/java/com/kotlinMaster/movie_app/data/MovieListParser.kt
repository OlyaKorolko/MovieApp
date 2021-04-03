package com.kotlinMaster.movie_app.data

import android.content.Context
import com.kotlinMaster.movie_app.R
import com.kotlinMaster.movie_app.dataholder.Actor
import com.kotlinMaster.movie_app.dataholder.Genre
import com.kotlinMaster.movie_app.dataholder.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
}

class MovieListParser(private val context: Context) : MovieRepository {
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    private var movies: List<Movie>? = null

    override suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val cachedMovies = movies
        if (cachedMovies != null) {
            cachedMovies
        } else {
            val moviesFromJson = loadMoviesFromJsonFile()
            movies = moviesFromJson
            moviesFromJson
        }
    }

    private suspend fun loadMoviesFromJsonFile(): List<Movie> {
        val genresMap = loadGenres()
        val actorsMap = loadActors()

        val data = readAssetFileToString("data.json")
        return parseMovies(data, genresMap, actorsMap)
    }

    private suspend fun loadGenres(): List<Genre> = withContext(Dispatchers.IO) {
        val data = readAssetFileToString("genres.json")
        val jsonGenres = jsonFormat.decodeFromString<List<JsonGenre>>(data)
        jsonGenres.map { jsonGenre -> Genre(id = jsonGenre.id, name = jsonGenre.name) }
    }

    private fun readAssetFileToString(fileName: String): String {
        val stream = context.assets.open(fileName)
        return stream.bufferedReader().readText()
    }

    private suspend fun loadActors(): List<Actor> = withContext(Dispatchers.IO) {
        val data = readAssetFileToString("people.json")
        val jsonActors = jsonFormat.decodeFromString<List<JsonActor>>(data)

        jsonActors.map { jsonActor ->
            Actor(
                id = jsonActor.id,
                name = jsonActor.name,
                imageUrl = jsonActor.profilePicture
            )
        }
    }

    private fun parseMovies(
        jsonString: String,
        genreData: List<Genre>,
        actors: List<Actor>
    ): List<Movie> {
        val genresMap = genreData.associateBy(Genre::id)
        val actorsMap = actors.associateBy(Actor::id)

        val jsonMovies = jsonFormat.decodeFromString<List<JsonMovie>>(jsonString)

        return jsonMovies.map { jsonMovie ->
            Movie(
                movieName = jsonMovie.title,
                storyline = jsonMovie.overview,
                cardPicture = jsonMovie.posterPicture,
                backgroundPicture = jsonMovie.backdropPicture,
                numberOfStars = (jsonMovie.ratings / 2).toInt(),
                reviews = jsonMovie.votesCount,
                ageBound = if (jsonMovie.adult) R.drawable.age_16 else R.drawable.age_13,
                duration = jsonMovie.runtime,
                genres = jsonMovie.genreIds.map { id ->
                    genresMap[id].orThrow { IllegalArgumentException("Genre not found") }
                },
                actorsList = jsonMovie.actors.map { id ->
                    actorsMap[id].orThrow { IllegalArgumentException("Actor not found") }
                },
                isLiked = false
            )
        }
    }

    /*override suspend fun loadMovie(movieId: Int): Movie? {
        val cachedMovies = movies ?: loadMovies()
        return cachedMovies.find { it.id == movieId }
    }*/

    private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
        return this ?: throw createThrowable()
    }
}