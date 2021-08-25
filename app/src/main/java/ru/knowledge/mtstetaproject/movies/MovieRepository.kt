package ru.knowledge.mtstetaproject.movies

import ru.knowledge.mtstetaproject.movies.data.*

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun getRefreshMovies(): List<MovieDto> {
        // Execute network request
        return getMovies()
    }

    suspend fun getMovieById(movieId: Long): MovieWithActors {
        return movieDao.getMovieWithActorsById(movieId)
    }

    suspend fun getMovies(): List<MovieDto> {
        val movies = movieDao.getMovies()
        if (movies.isEmpty()) {
            // Execute network request
            // delete old data from database and update
            movieDao.insertMovies(MoviesDataSourceImpl().getMovies())
            movieDao.insertActors(ActorsDataSource().getActors())
            movieDao.insertMovieActorCrossRefs(MovieActorCrossRefDataSource().getMovieActorCrossRef())
        } else {
            return movies
        }
        return movieDao.getMovies()
    }

    suspend fun getGenres(): List<GenreDto> {
        val genres = movieDao.getAllGenres()
        if (genres.isEmpty()) {
            // Execute network request
            // delete old data from database and update
            movieDao.insertGenres(GenresData().getGenres())
        } else {
            return genres
        }
        return movieDao.getAllGenres()
    }
}