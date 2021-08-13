package ru.knowledge.mtstetaproject.movies

import kotlinx.coroutines.delay
import ru.knowledge.mtstetaproject.movies.data.GenresData
import ru.knowledge.mtstetaproject.movies.data.MovieDto
import ru.knowledge.mtstetaproject.movies.data.MoviesDataSource
import ru.knowledge.mtstetaproject.movies.data.MoviesDataSourceImpl

object MovieRepository {
    private const val NUMBER_OF_MOVIES_ON_SCREEN = 6

    private val moviesDataSource: MoviesDataSource = MoviesDataSourceImpl()
    private val genresData: GenresData = GenresData()
    private val currentMovies = mutableListOf<MovieDto>()
    private var movieCounter = NUMBER_OF_MOVIES_ON_SCREEN

    fun getMovies() = currentMovies

    fun getGenres() = genresData.getGenres()

    suspend fun getRefreshMovies(): MutableList<MovieDto> {
        Thread.sleep(2000)
        //delay(2000)

        // Uncomment to check exception handling
        //throw IllegalStateException()
        return getNextMovies()
    }

    fun getNextMovies(): MutableList<MovieDto> {
        val allMovies = moviesDataSource.getMovies()
        currentMovies.apply {
            clear()
            addAll(allMovies.subList(movieCounter - NUMBER_OF_MOVIES_ON_SCREEN, movieCounter - 1))
        }
        movieCounter += NUMBER_OF_MOVIES_ON_SCREEN
        if (movieCounter > allMovies.size) {
            movieCounter = NUMBER_OF_MOVIES_ON_SCREEN
        }
        return currentMovies
    }
}