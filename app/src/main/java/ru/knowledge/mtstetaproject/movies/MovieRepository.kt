package ru.knowledge.mtstetaproject.movies

import ru.knowledge.mtstetaproject.movies.data.*

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun getRefreshMovies(): List<MovieDto> {
        // Execute network request
        return getMoviesFromDatabase()
    }

    suspend fun getMovieById(movieId: Long): MovieWithActors {
        return movieDao.getMovieWithActorsById(movieId)
    }

    suspend fun getMoviesFromDatabase(): List<MovieDto> {
        return movieDao.getMovies()
    }

    suspend fun getGenresFromDatabase(): List<GenreDto> {
        return movieDao.getAllGenres()
    }

    suspend fun populateDatabase() {
        movieDao.insertMovies(MoviesDataSourceImpl().getMovies())
        movieDao.insertActors(ActorsDataSource().getActors())
        movieDao.insertGenres(GenresData().getGenres())
        movieDao.insertMovieActorCrossRefs(MovieActorCrossRefDataSource().getMovieActorCrossRef())
    }

    suspend fun deleteAll() {
        movieDao.deleteAllActors()
        movieDao.deleteAllMovies()
        movieDao.deleteAllGenres()
        movieDao.deleteAllMovieActorCrossRef()
    }
}