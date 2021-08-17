package ru.knowledge.mtstetaproject.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.mtstetaproject.movies.data.MovieDto

class MovieDetailsViewModel : ViewModel() {

    val movie: LiveData<MovieDto> get() = mutableMovie
    private val mutableMovie = MutableLiveData<MovieDto>()

    fun getMovieById(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            // Thread.sleep(1000)
            mutableMovie.postValue(MovieRepository.getMovieById(movieId))
        }
    }
}