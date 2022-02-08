package com.mvzd.moviean.core.data

import com.mvzd.moviean.core.data.source.local.LocalDataSource
import com.mvzd.moviean.core.data.source.local.entity.MovieEntity
import com.mvzd.moviean.core.data.source.remote.ApiResponse
import com.mvzd.moviean.core.data.source.remote.NetworkBoundResource
import com.mvzd.moviean.core.data.source.remote.RemoteDataSource
import com.mvzd.moviean.core.data.source.remote.response.movie.MovieDetailResponse
import com.mvzd.moviean.core.data.source.remote.response.movie.MovieResponse
import com.mvzd.moviean.core.utils.AppExecutors
import com.mvzd.moviean.core.data.source.remote.Resource
import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.core.domain.repository.IMovieRepository
import com.mvzd.moviean.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            public override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override suspend fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()


            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        title = response.title,
                        originalTitle = response.originalTitle,
                        overview = response.overview,
                        popularity = response.popularity,
                        voteAverage = response.voteAverage,
                        voteCount = response.voteCount,
                        releaseDate = response.releaseDate,
                        posterPath = response.posterPath,
                        originalLanguage = response.originalLanguage,
                        budget = response.budget,
                        status = response.status,
                        homepage = response.homepage,
                        tagline = response.tagline,
                        runtime = response.runtime,
                        revenue = response.revenue,
                        imdbId = response.imdbId,
                        adult = response.adult,
                        backdropPath = response.backdropPath,
                        video = response.video
                    )
                    movieList.add(movie)
                }
                localDataSource.addMovies(movieList)
            }
        }.asFlow()


    override fun getMovieDetail(sid: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieDetailResponse?>() {
            public override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieDetail(sid).map {
                    DataMapper.mapMovieEntityToDomain(it)
                }
            }

            override suspend fun shouldFetch(data: Movie?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse?>> {
                return remoteDataSource.getMovieDetail(sid)
            }

            override suspend fun saveCallResult(data: MovieDetailResponse?) {}
        }.asFlow()
    }

    override fun getAllFavouriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavouriteMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setFavouriteMovie(movie: Movie, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavouriteMovie(
                DataMapper.mapMovieDomainToEntity(movie),
                state
            )
        }
    }

}