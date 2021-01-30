package com.kotlinMaster.movie_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), FragmentMoviesList.MoviesListClicker {
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

        rootFragment.apply {
            setClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, ROOT_FRAGMENT, rootFragment)
    }

    override fun openMovieDetails() {
        supportFragmentManager.beginTransaction()
            .apply {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                add(R.id.main_container, FragmentMoviesDetails())
                addToBackStack(null)
                commit()
            }
    }

    companion object {
        const val ROOT_FRAGMENT = "rootFragment"
    }
}