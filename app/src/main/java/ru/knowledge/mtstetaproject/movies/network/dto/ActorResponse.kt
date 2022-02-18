package ru.knowledge.mtstetaproject.movies.network.dto

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    val image: String?
)
