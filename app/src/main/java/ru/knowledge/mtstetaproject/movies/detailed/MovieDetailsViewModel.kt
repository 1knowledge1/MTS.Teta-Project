package ru.knowledge.mtstetaproject.movies.detailed

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.MovieRepository
import ru.knowledge.mtstetaproject.movies.database.MovieWithActors

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    val movie: LiveData<MovieWithActors> get() = mutableMovie
    private val mutableMovie = MutableLiveData<MovieWithActors>()

    fun getMovieById(movieId: Long) {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.d("MovieDetailsViewModel", "getMovieById exception $exception")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            val movieWithActors = repository.getMovieById(movieId)
            mutableMovie.postValue(movieWithActors)
        }
    }
}

class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}