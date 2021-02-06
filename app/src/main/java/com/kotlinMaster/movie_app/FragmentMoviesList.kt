package com.kotlinMaster.movie_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinMaster.movie_app.dataholder.MovieDataSource

class FragmentMoviesList : Fragment() {
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_movie_list)
        recycler?.adapter = AdapterMoviesList(activity as MoviesListClicker)
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    override fun onDetach() {
        recycler = null
        super.onDetach()
    }

    private fun updateData() {
        (recycler?.adapter as AdapterMoviesList).bindMovies(MovieDataSource().getMovies())
    }
}