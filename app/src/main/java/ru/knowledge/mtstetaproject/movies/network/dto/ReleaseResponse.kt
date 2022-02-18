package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class ReleaseResponse(
    @SerializedName("results")
    val releases: List<RegionsResponse>?
)
