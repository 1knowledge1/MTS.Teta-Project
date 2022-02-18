package ru.knowledge.mtstetaproject.movies.movies

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.MovieRepository
import ru.knowledge.mtstetaproject.movies.database.dto.GenreDto
import ru.knowledge.mtstetaproject.movies.database.dto.MovieDto


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movieList: LiveData<List<MovieDto>> get() = mutableMovieList
    private val mutableMovieList = MutableLiveData<List<MovieDto>>()

    val genreList: LiveData<List<GenreDto>> get() = mutableGenreList
    private val mutableGenreList = MutableLiveData<List<GenreDto>>()

    val errorState: LiveData<String> get() = mutableErrorState
    private val mutableErrorState = MutableLiveData<String>()

    val genresChecked = mutableSetOf<Long>()

    fun refreshMovies() {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.d("MovieViewModel", "refreshMovies exception $exception")
            mutableErrorState.postValue("Network error")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.getRefreshMovies().collect {
                it.fold(onSuccess = {
                    mutableMovieList.postValue(it)
                }, onFailure = {
                    mutableErrorState.postValue("Network error")
                })
            }
        }
    }

    fun resetError() {
        mutableErrorState.postValue("")
    }

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = repository.getGenres()
            mutableGenreList.postValue(genres)
        }
    }

    fun getMovies() {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.d("MovieViewModel", "refreshMovies exception $exception")
            mutableErrorState.postValue("Network error")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.getMovies().collect {
                it.fold(onSuccess = {
                    if (genresChecked.isEmpty()) {
                        mutableMovieList.postValue(it)
                    } else {
                        mutableMovieList.postValue(it.filter { movie ->
                            genresChecked.contains(movie.genre.id)
                        })
                    }
                }, onFailure = {
                    mutableErrorState.postValue("Network error")
                })
            }
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