package com.kotlinMaster.movie_app.dataholder

data class Movie (
    val picture: Int,
    val ageBound: Int,
    val isLiked: Boolean,
    val genres: String,
    val numberOfStars: Int,
    val reviews: Int,
    val movieName: String,
    val duration: Int
)