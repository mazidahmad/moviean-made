package com.mvzd.moviean.core.data.source.remote.response.tv

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListTvResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TvResponse>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
) : Parcelable
