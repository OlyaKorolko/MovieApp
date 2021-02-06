package com.kotlinMaster.movie_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kotlinMaster.movie_app.dataholder.Movie
import com.bumptech.glide.Glide

class AdapterMoviesList(private val clickListener: MoviesListClicker) :
    RecyclerView.Adapter<MovieCardViewHolder>() {
    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        return MovieCardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener.openMovieDetails(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class MovieCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val picture = itemView.findViewById<ImageView>(R.id.heroes)
    private val ageBound = itemView.findViewById<ImageView>(R.id.age)
    private val likeButton = itemView.findViewById<ImageView>(R.id.heart)
    private val genres = itemView.findViewById<TextView>(R.id.genres)
    private var listOfStars = putStarsIntoList()
    private val reviews = itemView.findViewById<TextView>(R.id.reviews_word)
    private val movieName = itemView.findViewById<TextView>(R.id.movie_name)
    private val duration = itemView.findViewById<TextView>(R.id.mins)

    private fun putStarsIntoList(): List<ImageView> {
        return mutableListOf(
            itemView.findViewById(R.id.star),
            itemView.findViewById(R.id.star2),
            itemView.findViewById(R.id.star3),
            itemView.findViewById(R.id.star4),
            itemView.findViewById(R.id.star5)
        )
    }

    private fun setStars(starNumber: Int) {
        var i = 1
        for (star in listOfStars) {
            if (i <= starNumber) {
                star.setColorFilter(ContextCompat.getColor(context, R.color.pink))
            } else {
                star.setColorFilter(ContextCompat.getColor(context, R.color.grey))
            }
            i++
        }
    }

    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.cardPicture)
            .into(picture)

        Glide.with(context)
            .load(movie.ageBound)
            .into(ageBound)

        if (movie.isLiked) {
            likeButton.setImageResource(R.drawable.pink_like)
        } else {
            likeButton.setImageResource(R.drawable.gray_like)
        }

        genres.text = movie.genres
        setStars(movie.numberOfStars)
        reviews.text = "${movie.reviews} REVIEWS"
        movieName.text = movie.movieName
        duration.text = "${movie.duration} MIN"
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface MoviesListClicker {
    fun openMovieDetails(movie: Movie)
}