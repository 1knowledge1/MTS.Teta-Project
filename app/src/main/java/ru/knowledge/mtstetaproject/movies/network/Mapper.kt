package ru.knowledge.mtstetaproject.movies.network

import ru.knowledge.mtstetaproject.movies.database.dto.ActorDto
import ru.knowledge.mtstetaproject.movies.database.dto.GenreDto
import ru.knowledge.mtstetaproject.movies.database.dto.MovieDto
import ru.knowledge.mtstetaproject.movies.network.dto.ActorResponse
import ru.knowledge.mtstetaproject.movies.network.dto.MovieResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieResponse.toMovieDto(): MovieDto {
    var rateScore = 0
    if (voteAverage != null) {
        rateScore = (voteAverage / 2).roundToInt()
    }
    var genre = GenreDto(0, "")
    if (genres != null && genres.isNotEmpty()) {
        genre = GenreDto(genres[0].id ?: 0, genres[0].name ?: "")
    }
    var date = Date(0)
    if (releaseDate != null) {
        date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(releaseDate) ?: Date(0)
    }
    val image = IMAGE_BASE_URL + image
    var ageRestriction = releaseDates?.releases?.find { it.isoCode == "RU" }
        ?.releaseInfo?.first()?.certification
    if (ageRestriction == null || ageRestriction.isEmpty()) {
        val usAgeRestriction = releaseDates?.releases?.find { it.isoCode == "US" }
            ?.releaseInfo?.first()?.certification
        ageRestriction = when (usAgeRestriction) {
            "G" -> "0+"
            "PG" -> "6+"
            "PG-13" -> "12+"
            "R" -> "18+"
            "NC-17" -> "18+"
            else -> "NR"
        }
    }
    return MovieDto(id, title ?: "", description ?: "",
        rateScore, ageRestriction, image, genre, date)
}

fun ActorResponse.toActorDto(): ActorDto? {
    val imageUrl = IMAGE_BASE_URL + image
    return if (id != null && name != null) ActorDto(id, name, imageUrl) else null
}