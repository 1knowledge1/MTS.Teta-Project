package ru.knowledge.mtstetaproject.movies.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreDto(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)