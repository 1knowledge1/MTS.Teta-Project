package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("results")
    val moviesId: List<MoviesIdResponse>?
)
