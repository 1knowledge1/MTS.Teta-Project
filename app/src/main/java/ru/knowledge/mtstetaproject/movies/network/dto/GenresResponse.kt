package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?
)