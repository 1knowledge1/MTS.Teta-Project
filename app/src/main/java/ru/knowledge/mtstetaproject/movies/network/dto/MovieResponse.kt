package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("poster_path")
    val image: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("genres")
    val genres: List<GenresResponse>?,
    @SerializedName("credits")
    val credits: CastResponse?,
    @SerializedName("release_dates")
    val releaseDates: ReleaseResponse?
)
