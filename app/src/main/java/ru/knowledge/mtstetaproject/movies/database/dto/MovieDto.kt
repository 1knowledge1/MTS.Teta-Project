package ru.knowledge.mtstetaproject.movies.database.dto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movies")
data class MovieDto(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "rate_score")
    val rateScore: Int,
    @ColumnInfo(name = "age_restriction")
    val ageRestriction: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @Embedded
    val genre: GenreDto,
    @ColumnInfo(name = "release_date")
    val releaseDate: Date,
)