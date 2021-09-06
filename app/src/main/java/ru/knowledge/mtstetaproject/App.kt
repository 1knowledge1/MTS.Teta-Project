package ru.knowledge.mtstetaproject

import android.app.Application
import ru.knowledge.mtstetaproject.movies.MovieRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { MovieRepository(database.movieDao()) }
}