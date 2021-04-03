package com.kotlinMaster.movie_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinMaster.movie_app.data.MovieListParser
import com.kotlinMaster.movie_app.dataholder.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment() {
    private var recycler: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_movie_list)
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler?.adapter = AdapterMoviesList(activity as MoviesListClicker)

        loadData(view)
    }

    private fun loadData(view: View) {
        var moviesList: List<Movie>? = null
        scope.launch {
            val job = async { moviesList = MovieListParser(view.context).loadMovies() }
            job.await()
            activity?.runOnUiThread {
                (recycler?.adapter as AdapterMoviesList).bindMovies(moviesList!!)
            }
        }
    }

    override fun onDetach() {
        recycler = null
        super.onDetach()
    }
}