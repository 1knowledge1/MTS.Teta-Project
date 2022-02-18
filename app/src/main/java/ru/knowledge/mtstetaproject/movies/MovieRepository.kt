package ru.knowledge.mtstetaproject.movies

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.knowledge.mtstetaproject.movies.data.*
import ru.knowledge.mtstetaproject.movies.database.MovieDao
import ru.knowledge.mtstetaproject.movies.database.MovieWithActors
import ru.knowledge.mtstetaproject.movies.database.dto.*
import ru.knowledge.mtstetaproject.movies.network.TmdbApiService
import ru.knowledge.mtstetaproject.movies.network.dto.MovieResponse
import ru.knowledge.mtstetaproject.movies.network.dto.MoviesIdResponse
import ru.knowledge.mtstetaproject.movies.network.toActorDto
import ru.knowledge.mtstetaproject.movies.network.toMovieDto
import java.io.IOException
import java.lang.Exception
import java.util.*

class MovieRepository(private val movieDao: MovieDao, private val apiService: TmdbApiService) {

    suspend fun getRefreshMovies(): Flow<Result<List<MovieDto>>> {
        return flow {
            Log.d(TAG, "Start loading from Network.")
            val loadedMovies = loadMovies()
            Log.d(TAG, "End of loading.")
            if (loadedMovies == null) {
                emit(Result.failure<List<MovieDto>>(IOException()))
            } else {
                val moviesDto = saveMoviesToDatabase(loadedMovies)
                saveActorsToDatabase(loadedMovies)
                saveTableInfoToDatabase()
                Log.d(TAG, "Send data to UI")
                emit(Result.success(moviesDto))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(movieId: Long): MovieWithActors {
        return movieDao.getMovieWithActorsById(movieId)
    }

    suspend fun getGenres(): List<GenreDto> {
        val genres = movieDao.getAllGenres()
        if (genres.isEmpty()) {
            // TODO: Execute network request
            movieDao.insertGenres(GenresData().getGenres())
        } else {
            return genres
        }
        return movieDao.getAllGenres()
    }

    suspend fun getMovies(): Flow<Result<List<MovieDto>>> {
        return flow {
            val movies = movieDao.getMovies()
            if (movies != null) {
                emit(Result.success(movies.toList()))
                Log.d(TAG, "Send data to UI from DB.")
            }
            val lastUpdate = movieDao.getTableInfo("movies")?.lastUpdate
            if (shouldFetch(lastUpdate)) {
                Log.d(TAG, "Should fetch data.")
                Log.d(TAG, "Start loading from Network.")
                val loadedMovies = loadMovies()
                Log.d(TAG, "End of loading.")
                if (loadedMovies == null) {
                    emit(Result.failure<List<MovieDto>>(IOException()))
                } else {
                    val moviesDto = saveMoviesToDatabase(loadedMovies)
                    saveActorsToDatabase(loadedMovies)
                    saveTableInfoToDatabase()
                    Log.d(TAG, "Send data to UI")
                    emit(Result.success(moviesDto))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun loadMovies(): List<MovieResponse>? {
        var response: List<MoviesIdResponse>? = null
        try {
            response = apiService.getPopularMovies()?.moviesId
            Log.d(TAG, "received IDs: $response")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "Network request for popular movies failed: $e")
        }
        if (response == null) return null

        var moviesResponse = mutableListOf<MovieResponse>()
        try {
            var movieResponse: MovieResponse?
            for (movieId in response) {
                movieResponse = apiService.getMovieById(movieId.id)
                if (movieResponse != null) {
                    moviesResponse.add(movieResponse)
                    Log.d(TAG, "received data for id = ${movieResponse.id}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            moviesResponse = mutableListOf()
            Log.d(TAG, "Network request for detailed movie failed: $e")
        }
        Log.d(TAG, "received ${moviesResponse.size} movies.")
        return if (moviesResponse.size > 0) moviesResponse else null
    }

    private suspend fun saveMoviesToDatabase(loadedData: List<MovieResponse>): List<MovieDto> {
        val moviesDto = loadedData.map { it.toMovieDto() }
        Log.d(TAG, "Delete movies from DB.")
        movieDao.deleteAllMovies()
        Log.d(TAG, "Insert movies in DB.")
        movieDao.insertMovies(moviesDto)
        return moviesDto
    }

    private suspend fun saveActorsToDatabase(loadedData: List<MovieResponse>): List<ActorDto> {
        val actors = mutableListOf<ActorDto>()
        val movieActorCrossRefs = mutableListOf<MovieActorCrossRef>()
        for (movie in loadedData) {
            if (movie.credits?.cast != null) {
                for (actor in movie.credits.cast) {
                    val actorDto = actor.toActorDto()
                    if (actorDto != null) {
                        actors.add(actorDto)
                        movieActorCrossRefs.add(MovieActorCrossRef(movie.id, actorDto.id))
                    }
                }
            }
        }
        Log.d(TAG, "Delete actors from DB.")
        movieDao.deleteAllActors()
        Log.d(TAG, "Insert actors in DB.")
        movieDao.insertActors(actors)
        Log.d(TAG, "Delete movie-actors cross ref from DB.")
        movieDao.deleteAllMovieActorCrossRef()
        Log.d(TAG, "Insert movie-actors cross ref in DB.")
        movieDao.insertMovieActorCrossRefs(movieActorCrossRefs)
        return actors.toList()
    }

    private suspend fun saveTableInfoToDatabase(): TableInfo {
        Log.d(TAG, "Update 'last update'")
        val tableInfo = TableInfo("movies", Calendar.getInstance().time)
        movieDao.insertTableInfo(tableInfo)
        return tableInfo
    }

    private fun shouldFetch(lastUpdate: Date?): Boolean {
        if (lastUpdate == null) return true
        val currentTime = Calendar.getInstance().time
        val dayOfLastUpdate = GregorianCalendar().apply {
            time = lastUpdate
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 99)
        }.time
        return currentTime.after(dayOfLastUpdate)
    }

    companion object {
        const val TAG = "MovieRepository"
    }
}