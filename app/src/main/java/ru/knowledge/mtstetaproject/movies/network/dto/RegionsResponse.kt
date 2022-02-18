package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class RegionsResponse(
    @SerializedName("iso_3166_1")
    val isoCode: String?,
    @SerializedName("release_dates")
    val releaseInfo: List<ReleaseInfoResponse>?
)
