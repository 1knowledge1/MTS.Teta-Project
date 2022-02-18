package ru.knowledge.mtstetaproject

import android.app.Application
import ru.knowledge.mtstetaproject.movies.MovieRepository
import ru.knowledge.mtstetaproject.movies.network.TmdbApiService

class App : Application() {
    val tmdbApiService by lazy { TmdbApiService.create() }
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { MovieRepository(database.movieDao(), tmdbApiService) }
}