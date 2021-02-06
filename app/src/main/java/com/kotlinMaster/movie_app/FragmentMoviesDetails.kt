package com.kotlinMaster.movie_app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinMaster.movie_app.dataholder.Movie

class FragmentMoviesDetails : Fragment() {
    private var listener: OnBackButtonClicked? = null
    private var parentRecycler: RecyclerView? = null
    private var chosenMovie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chosenMovie = arguments?.getParcelable(MOVIE_CHOSEN)
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentRecycler = view.findViewById(R.id.movie_details_container)
        parentRecycler?.adapter = MovieDetailsAdapter(activity as OnBackButtonClicked)
        parentRecycler?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBackButtonClicked) {
            listener = context
        } else {
            throw ClassCastException("$context must implement FragmentMoviesDetails.OnBackButtonClicked")
        }
    }

    override fun onStart() {
        super.onStart()

        (parentRecycler?.adapter as MovieDetailsAdapter).bindMovie(chosenMovie!!)
    }

    override fun onDetach() {
        parentRecycler = null
        listener = null

        super.onDetach()
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_CHOSEN, movie)
            fragment.arguments = bundle
            return fragment
        }
    }
}

interface OnBackButtonClicked {
    fun onClick()
}

const val MOVIE_CHOSEN = "movie"