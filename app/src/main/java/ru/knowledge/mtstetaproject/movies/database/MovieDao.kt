package ru.knowledge.mtstetaproject.movies.database

import androidx.room.*
import ru.knowledge.mtstetaproject.movies.database.dto.*

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movies")
    suspend fun getMoviesWithActors(): List<MovieWithActors>

    @Transaction
    @Query("SELECT * FROM movies WHERE movie_id == :movieId")
    suspend fun getMovieWithActorsById(movieId: Long): MovieWithActors

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieDto>?

    @Query("SELECT * FROM genres")
    suspend fun getAllGenres(): List<GenreDto>

    @Query("SELECT * FROM information_table WHERE table_name == :tableName")
    suspend fun getTableInfo(tableName: String): TableInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActorCrossRefs(crossRefs: List<MovieActorCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTableInfo(info: TableInfo)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM actors")
    suspend fun deleteAllActors()

    @Query("DELETE FROM movie_actor_cross_ref")
    suspend fun deleteAllMovieActorCrossRef()

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()
}