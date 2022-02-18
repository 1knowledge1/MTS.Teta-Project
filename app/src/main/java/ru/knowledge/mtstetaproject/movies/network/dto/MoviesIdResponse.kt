package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class MoviesIdResponse(
    @SerializedName("id")
    val id: Long
)
