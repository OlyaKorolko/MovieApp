package com.kotlinMaster.movie_app.dataholder

import java.io.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val imageUrl: String
    ): Serializable