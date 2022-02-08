package com.mvzd.moviean.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

	@ColumnInfo(name = "overview")
	var overview: String? = null,

	@ColumnInfo(name = "originalLanguage")
	var originalLanguage: String? = null,

	@ColumnInfo(name = "originalTitle")
	var originalTitle: String? = null,

	@ColumnInfo(name = "video")
	var video: Boolean? = null,

	@ColumnInfo(name = "title")
	var title: String? = null,

	@ColumnInfo(name = "posterPath")
	var posterPath: String? = null,

	@ColumnInfo(name = "backdropPath")
	var backdropPath: String? = null,

	@ColumnInfo(name = "releaseDate")
	var releaseDate: String? = null,

	@ColumnInfo(name = "popularity")
	var popularity: Double? = null,

	@ColumnInfo(name = "voteAverage")
	var voteAverage: Double? = null,

	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "id")
	var id: Int,

	@ColumnInfo(name = "adult")
	var adult: Boolean? = null,

	@ColumnInfo(name = "voteCount")
	var voteCount: Int? = null,

	@ColumnInfo(name = "isFavourite")
	var isFavourite: Boolean? = null,

	@ColumnInfo(name = "imdbId")
	var imdbId: String? = null,

	@ColumnInfo(name = "revenue")
	var revenue: Int? = null,

	@ColumnInfo(name = "budget")
	var budget: Int? = null,

	@ColumnInfo(name = "runtime")
	var runtime: Int? = null,

	@ColumnInfo(name = "tagline")
	var tagline: String? = null,

	@ColumnInfo(name = "homepage")
	var homepage: String? = null,

	@ColumnInfo(name = "status")
	var status: String? = null
)
