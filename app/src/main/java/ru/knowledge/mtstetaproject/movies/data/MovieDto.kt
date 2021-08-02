package ru.knowledge.mtstetaproject.movies.data

data class MovieDto(
    val id: Int,
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String,
    val genre: GenreDto,
    val releaseDate: String,
    val actors: List<ActorDto>
)