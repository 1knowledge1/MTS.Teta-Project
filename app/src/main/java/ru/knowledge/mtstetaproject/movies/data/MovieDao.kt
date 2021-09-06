package ru.knowledge.mtstetaproject.movies.data

import androidx.room.*

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movies")
    suspend fun getMoviesWithActors(): List<MovieWithActors>

    @Transaction
    @Query("SELECT * FROM movies WHERE movie_id == :movieId")
    suspend fun getMovieWithActorsById(movieId: Long): MovieWithActors

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieDto>

    @Query("SELECT * FROM genres")
    suspend fun getAllGenres(): List<GenreDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActorCrossRefs(crossRefs: List<MovieActorCrossRef>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM actors")
    suspend fun deleteAllActors()

    @Query("DELETE FROM movie_actor_cross_ref")
    suspend fun deleteAllMovieActorCrossRef()

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()
}