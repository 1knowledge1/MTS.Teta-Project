package ru.knowledge.mtstetaproject.movies.data

object MoviesModel {
    private val moviesDataSource: MoviesDataSource = MoviesDataSourceImpl()
    private val genresData: GenresData = GenresData()
    val genresChecked = mutableSetOf<Int>()

    fun getMovies() = moviesDataSource.getMovies()

    fun getGenres() = genresData.getGenres()
}