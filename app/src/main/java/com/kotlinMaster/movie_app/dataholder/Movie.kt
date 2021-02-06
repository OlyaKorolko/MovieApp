package com.kotlinMaster.movie_app.dataholder

import android.os.Parcel
import android.os.Parcelable

data class Movie (
    val cardPicture: Int,
    val backgroundPicture: Int,
    val ageBound: Int,
    val isLiked: Boolean,
    val genres: String?,
    val numberOfStars: Int,
    val reviews: Int,
    val storyline: String?,
    val movieName: String?,
    val duration: Int,
    val actorsList: List<Actor>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        listOf<Actor>().apply {
            parcel.readList(this, Actor::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cardPicture)
        parcel.writeInt(backgroundPicture)
        parcel.writeInt(ageBound)
        parcel.writeByte(if (isLiked) 1 else 0)
        parcel.writeString(genres)
        parcel.writeInt(numberOfStars)
        parcel.writeInt(reviews)
        parcel.writeString(storyline)
        parcel.writeString(movieName)
        parcel.writeInt(duration)
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