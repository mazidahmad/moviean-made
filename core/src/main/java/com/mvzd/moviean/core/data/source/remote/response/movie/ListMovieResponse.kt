package com.mvzd.moviean.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieResponse>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)
