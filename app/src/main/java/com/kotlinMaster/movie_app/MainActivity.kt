package com.kotlinMaster.movie_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.kotlinMaster.movie_app.dataholder.Movie


class MainActivity : AppCompatActivity(), MoviesListClicker,
    OnBackButtonClicked {
    private lateinit var rootFragment: FragmentMoviesList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            rootFragment = FragmentMoviesList()
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.main_container, rootFragment)
                    commit()
                }
        } else {
            rootFragment = supportFragmentManager.getFragment(
                savedInstanceState,
                ROOT_FRAGMENT
            ) as FragmentMoviesList
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, ROOT_FRAGMENT, rootFragment)
    }

    override fun openMovieDetails(movie: Movie) {

        supportFragmentManager.beginTransaction()
            .apply {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                add(R.id.main_container, FragmentMoviesDetails.newInstance(movie))
                addToBackStack(MOVIE_DETAILS_FRAGMENT)
                commit()
            }
    }

    override fun onClick() {
        supportFragmentManager.popBackStack(
            MOVIE_DETAILS_FRAGMENT,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {
        const val ROOT_FRAGMENT = "rootFragment"
        const val MOVIE_DETAILS_FRAGMENT = "Movie Details"
    }
}