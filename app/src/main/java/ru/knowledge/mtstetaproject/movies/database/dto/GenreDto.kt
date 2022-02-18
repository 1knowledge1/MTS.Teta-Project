package ru.knowledge.mtstetaproject.movies.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreDto(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String
)