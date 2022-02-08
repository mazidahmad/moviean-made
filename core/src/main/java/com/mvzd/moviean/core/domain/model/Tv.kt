package com.mvzd.moviean.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tv(
	val firstAirDate: String? = null,
	val overview: String? = null,
	val originalLanguage: String? = null,
	val posterPath: String? = null,
	val backdropPath: String? = null,
	val originalName: String? = null,
	val popularity: Double? = null,
	val voteAverage: Double? = null,
	val name: String? = null,
	val id: Int,
	val voteCount: Int? = null,
	var isFavourite: Boolean? = null
) : Parcelable
