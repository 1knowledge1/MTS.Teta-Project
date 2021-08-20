package ru.knowledge.mtstetaproject.movies

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.data.GenreDto
import ru.knowledge.mtstetaproject.movies.data.MovieDto


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movieList: LiveData<List<MovieDto>> get() = mutableMovieList
    private val mutableMovieList = MutableLiveData<List<MovieDto>>()

    val genreList: LiveData<List<GenreDto>> get() = mutableGenreList
    private val mutableGenreList = MutableLiveData<List<GenreDto>>()

    val errorState: LiveData<String> get() = mutableErrorState
    private val mutableErrorState = MutableLiveData<String>()

    val genresChecked = mutableSetOf<Int>()

    init {
        populateDatabase()
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

    fun getGenresFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = repository.getGenresFromDatabase()
            mutableGenreList.postValue(genres)
        }
    }

    fun getMoviesFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = repository.getMoviesFromDatabase()
            mutableMovieList.postValue(movies)
        }
    }

    private fun populateDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.populateDatabase()
        }
    }

    fun deleteAllDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}