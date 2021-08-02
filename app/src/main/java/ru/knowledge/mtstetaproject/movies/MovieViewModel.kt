package ru.knowledge.mtstetaproject.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.data.GenreDto
import ru.knowledge.mtstetaproject.movies.data.MovieDto


class MovieViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()

    val movieList: LiveData<List<MovieDto>> get() = _movieList
    private val _movieList = MutableLiveData<List<MovieDto>>()

    val errorState: LiveData<String> get() = _errorState
    private val _errorState = MutableLiveData<String>()

    val genresChecked = mutableSetOf<Int>()

    init {
        _movieList.postValue(repository.getNextMovies())
    }

    fun refreshMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = repository.getRefreshMovies()
                _movieList.postValue(movies)
            } catch (e: Exception) {
                Log.d("MyException", "$e")
                _errorState.postValue("Network error")
            }
        }
    }

    fun resetError() {
        _errorState.postValue("")
    }

    fun getGenres(): List<GenreDto> {
        return repository.getGenres()
    }

    fun getMovieById(movieId: Int): MovieDto {
        val allMovies = repository.getMovies()
        return allMovies.find { it.id == movieId } ?: allMovies.first()
    }
}