package com.mvzd.moviean.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvs")
data class TvEntity(
    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "posterPath")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String? = null,

    @ColumnInfo(name = "originalName")
    val originalName: String? = null,

    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int? = null,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean? = null
)