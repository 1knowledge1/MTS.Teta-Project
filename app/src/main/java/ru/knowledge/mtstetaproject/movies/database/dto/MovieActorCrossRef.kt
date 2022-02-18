package ru.knowledge.mtstetaproject.movies.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "movie_actor_cross_ref",
    primaryKeys = ["movie_id", "actor_id"]
)
data class MovieActorCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    @ColumnInfo(name = "actor_id")
    val actorId: Long
)
