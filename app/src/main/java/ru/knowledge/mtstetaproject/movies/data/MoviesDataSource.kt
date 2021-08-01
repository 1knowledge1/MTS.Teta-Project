package ru.knowledge.mtstetaproject.movies.data

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}