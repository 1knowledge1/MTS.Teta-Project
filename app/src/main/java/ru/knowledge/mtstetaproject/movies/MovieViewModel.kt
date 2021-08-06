package ru.knowledge.mtstetaproject.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.data.GenreDto
import ru.knowledge.mtstetaproject.movies.data.MovieDto


class MovieViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()

    val movieList: LiveData<List<MovieDto>> get() = mutableMovieList
    private val mutableMovieList = MutableLiveData<List<MovieDto>>()

    val errorState: LiveData<String> get() = mutableErrorState
    private val mutableErrorState = MutableLiveData<String>()

    val genresChecked = mutableSetOf<Int>()

    init {
        mutableMovieList.postValue(repository.getNextMovies())
    }

    fun refreshMovies() {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.d("MovieViewModel", "refreshMovies exception $exception")
            mutableErrorState.postValue("Network error")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            val movies = repository.getRefreshMovies()
            mutableMovieList.postValue(movies)
        }
    }

    fun resetError() {
        mutableErrorState.postValue("")
    }

    fun getGenres(): List<GenreDto> {
        return repository.getGenres()
    }

    fun getMovieById(movieId: Int): MovieDto {
        val allMovies = repository.getMovies()
        return allMovies.find { it.id == movieId } ?: allMovies.first()
    }
}