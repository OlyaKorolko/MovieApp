package com.kotlinMaster.movie_app

import com.kotlinMaster.movie_app.data.MovieListParser

internal interface MovieListParserProvider {
    fun provideMovieListParser(): MovieListParser
}