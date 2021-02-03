package com.kotlinMaster.movie_app

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinMaster.movie_app.dataholder.ActorsDataSource
import com.kotlinMaster.movie_app.dataholder.MovieDataSource
import java.lang.ClassCastException

class FragmentMoviesDetails : Fragment() {
    private var backButton: TextView? = null
    private var listener: OnBackButtonClicked? = null
    private var actorsRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actorsRecycler = view.findViewById(R.id.actors_container)
        actorsRecycler?.adapter = ActorsAdapter()
        actorsRecycler?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        actorsRecycler?.addItemDecoration(HorizontalSpacingItemDecoration())

        backButton = view.findViewById<TextView>(R.id.back_text).apply {
            setOnClickListener { listener?.onClick() }
        }
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

        updateData()
    }


    override fun onDetach() {
        actorsRecycler = null
        listener = null

        super.onDetach()
    }

    private fun updateData() {
        (actorsRecycler?.adapter as ActorsAdapter).bindActors(ActorsDataSource().getActors())
    }
}

interface OnBackButtonClicked {
    fun onClick()
}