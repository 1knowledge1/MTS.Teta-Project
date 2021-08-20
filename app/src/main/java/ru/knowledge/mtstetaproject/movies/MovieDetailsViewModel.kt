package ru.knowledge.mtstetaproject.movies

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.data.MovieWithActors

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    val movie: LiveData<MovieWithActors> get() = mutableMovie
    private val mutableMovie = MutableLiveData<MovieWithActors>()

    fun getMovieById(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
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