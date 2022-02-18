package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class ReleaseInfoResponse(
    @SerializedName("certification")
    val certification: String?,
    @SerializedName("release_date")
    val releaseDate: String?
)
