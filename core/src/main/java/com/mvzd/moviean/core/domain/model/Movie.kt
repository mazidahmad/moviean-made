package com.mvzd.moviean.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
	val overview: String? = null,
	val originalLanguage: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val posterPath: String? = null,
	val backdropPath: String? = null,
	val releaseDate: String? = null,
	val popularity: Double? = null,
	val voteAverage: Double? = null,
	val id: Int,
	val adult: Boolean? = null,
	val voteCount: Int? = null,
	val isFavourite: Boolean? = null,
	val imdbId: String? = null,
	val revenue: Int? = null,
	val budget: Int? = null,
	val runtime: Int? = null,
	val tagline: String? = null,
	val homepage: String? = null,
	val status: String? = null
) : Parcelable
