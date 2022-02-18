package ru.knowledge.mtstetaproject.movies.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
data class ActorDto(
    @PrimaryKey
    @ColumnInfo(name = "actor_id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)