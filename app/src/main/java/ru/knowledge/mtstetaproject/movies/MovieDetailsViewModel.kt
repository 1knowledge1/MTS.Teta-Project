package ru.knowledge.mtstetaproject.movies

import androidx.lifecycle.ViewModel
import ru.knowledge.mtstetaproject.movies.data.MovieDto

class MovieDetailsViewModel : ViewModel() {

    fun getMovieById(movieId: Int): MovieDto {
        val allMovies = MovieRepository.getMovies()
        return allMovies.find { it.id == movieId } ?: allMovies.first()
    }
}