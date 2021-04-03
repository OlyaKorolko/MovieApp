package com.kotlinMaster.movie_app.dataholder

import android.os.Parcel
import android.os.Parcelable

data class Movie (
    val ageBound: Int,
    val movieName: String?,
    val genres: List<Genre>,
    val duration: Int,
    val reviews: Int,
    val isLiked: Boolean,
    val numberOfStars: Int,
    val cardPicture: String?,
    val backgroundPicture: String?,
    val storyline: String?,
    val actorsList: List<Actor>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        listOf<Genre>().apply {
            parcel.readList(this, Genre::class.java.classLoader)
        },
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        listOf<Actor>().apply {
            parcel.readList(this, Actor::class.java.classLoader)
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ageBound)
        parcel.writeString(movieName)
        parcel.writeInt(duration)
        parcel.writeInt(reviews)
        parcel.writeByte(if (isLiked) 1 else 0)
        parcel.writeInt(numberOfStars)
        parcel.writeString(cardPicture)
        parcel.writeString(backgroundPicture)
        parcel.writeString(storyline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}