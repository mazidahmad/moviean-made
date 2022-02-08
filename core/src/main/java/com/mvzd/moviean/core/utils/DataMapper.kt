package com.mvzd.moviean.core.utils

import com.mvzd.moviean.core.data.source.local.entity.MovieEntity
import com.mvzd.moviean.core.data.source.local.entity.TvEntity
import com.mvzd.moviean.core.data.source.remote.response.movie.MovieResponse
import com.mvzd.moviean.core.data.source.remote.response.tv.TvResponse
import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.core.domain.model.Tv

object DataMapper {
    fun mapListMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                adult = it.adult,
                voteCount = it.voteCount,
                imdbId = it.imdbId,
                revenue = it.revenue,
                budget = it.budget,
                runtime = it.runtime,
                tagline = it.tagline,
                homepage = it.homepage,
                status = it.status
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieResponsesToEntity(input: MovieResponse): MovieEntity {
        return MovieEntity(
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            video = input.video,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            id = input.id,
            adult = input.adult,
            voteCount = input.voteCount,
            imdbId = input.imdbId,
            revenue = input.revenue,
            budget = input.budget,
            runtime = input.runtime,
            tagline = input.tagline,
            homepage = input.homepage,
            status = input.status
        )
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> = input.map {
        Movie(
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            video = it.video,
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            releaseDate = it.releaseDate,
            popularity = it.popularity,
            voteAverage = it.voteAverage,
            id = it.id,
            adult = it.adult,
            voteCount = it.voteCount,
            isFavourite = it.isFavourite,
            imdbId = it.imdbId,
            revenue = it.revenue,
            budget = it.budget,
            runtime = it.runtime,
            tagline = it.tagline,
            homepage = it.homepage,
            status = it.status
        )
    }

    fun mapMovieEntityToDomain(input: MovieEntity): Movie = Movie(
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        id = input.id,
        adult = input.adult,
        voteCount = input.voteCount,
        isFavourite = input.isFavourite,
        imdbId = input.imdbId,
        revenue = input.revenue,
        budget = input.budget,
        runtime = input.runtime,
        tagline = input.tagline,
        homepage = input.homepage,
        status = input.status
    )

    fun mapMovieDomainToEntity(input: Movie) =
        MovieEntity(
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            video = input.video,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            id = input.id,
            adult = input.adult,
            voteCount = input.voteCount
        )

    fun mapListTvResponsesToEntities(input: List<TvResponse>): List<TvEntity> {
        val tvList = ArrayList<TvEntity>()
        input.map {
            val tv = TvEntity(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalName = it.originalName,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                voteCount = it.voteCount
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapTvResponsesToEntity(input: TvResponse): TvEntity {
        return TvEntity(
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalName = input.originalName,
            name = input.name,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            firstAirDate = input.firstAirDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            id = input.id,
            voteCount = input.voteCount
        )
    }

    fun mapTvEntitiesToDomain(input: List<TvEntity>): List<Tv> = input.map {
        Tv(
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            originalName = it.originalName,
            name = it.name,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            popularity = it.popularity,
            voteAverage = it.voteAverage,
            id = it.id,
            voteCount = it.voteCount
        )
    }

    fun mapTveEntityToDomain(input: TvEntity): Tv = Tv(
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalName = input.originalName,
        name = input.name,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        firstAirDate = input.firstAirDate,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        isFavourite = input.isFavourite,
        id = input.id,
        voteCount = input.voteCount
    )

    fun mapTvDomainToEntity(input: Tv) =
        TvEntity(
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalName = input.originalName,
            name = input.name,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            firstAirDate = input.firstAirDate,
            popularity = input.popularity,
            isFavourite = input.isFavourite,
            voteAverage = input.voteAverage,
            id = input.id,
            voteCount = input.voteCount
        )
}