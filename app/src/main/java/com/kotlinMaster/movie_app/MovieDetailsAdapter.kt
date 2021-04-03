package com.kotlinMaster.movie_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinMaster.movie_app.dataholder.Movie

class MovieDetailsAdapter(private val clickListener: OnBackButtonClicked) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private var movie: Movie? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movie!!)

        if (movie!!.actorsList.isNotEmpty()) {
            val childLayoutManager =
                LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)

            holder.recyclerView.apply {
                layoutManager = childLayoutManager
                adapter = ActorsAdapter()
                addItemDecoration(HorizontalSpacingItemDecoration())
            }

            (holder.recyclerView.adapter as ActorsAdapter).bindActors(movie!!.actorsList)
        }

        holder.itemView.findViewById<TextView>(R.id.back_text).setOnClickListener {
            clickListener.onClick()
        }
    }

    override fun getItemCount(): Int = 1

    fun bindMovie(newMovie: Movie) {
        movie = newMovie
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val recyclerView: RecyclerView = itemView.findViewById(R.id.actors_recycler_view)
    private val backgroundPicture = itemView.findViewById<ImageView>(R.id.picture_background)
    private val age = itemView.findViewById<ImageView>(R.id.age)
    private val movieName = itemView.findViewById<TextView>(R.id.movie_name)
    private val genres = itemView.findViewById<TextView>(R.id.genres)
    private var listOfStars = putStarsIntoList()
    private val reviews = itemView.findViewById<TextView>(R.id.reviews_word)
    private val storyline = itemView.findViewById<TextView>(R.id.storyline)


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
            .load(movie.backgroundPicture)
            .into(backgroundPicture)

        Glide.with(context)
            .load(movie.ageBound)
            .into(age)

        movieName.text = movie.movieName
        genres.text = movie.genres.joinToString { it.name }
        setStars(movie.numberOfStars)
        reviews.text = "${movie.reviews} REVIEWS"
        storyline.text = movie.storyline
    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context