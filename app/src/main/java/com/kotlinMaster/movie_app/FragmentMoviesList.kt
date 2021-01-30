package com.kotlinMaster.movie_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class FragmentMoviesList : Fragment() {
    private var movieCardClicked: ImageView? = null
    private var listener: MoviesListClicker? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieCardClicked = view.findViewById<ImageView>(R.id.transparent_screen).apply {
            setOnClickListener { listener?.openMovieDetails() }
        }
    }

    fun setClickListener(l: MoviesListClicker?) {
        listener = l
    }

    interface MoviesListClicker {
        fun openMovieDetails()
    }
}