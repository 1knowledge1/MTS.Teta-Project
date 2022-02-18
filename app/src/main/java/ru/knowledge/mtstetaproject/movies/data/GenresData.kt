package ru.knowledge.mtstetaproject.movies.data

import ru.knowledge.mtstetaproject.movies.database.dto.GenreDto

class GenresData {
    fun getGenres() = listOf(
        GenreDto(28, "Боевик"),
        GenreDto(12, "Приключения"),
        GenreDto(16, "Мультик"),
        GenreDto(35, "Комедия"),
        GenreDto(80, "Криминал"),
        GenreDto(99, "Документальное"),
        GenreDto(18, "Драма"),
        GenreDto(10751, "Семейный"),
        GenreDto(14, "Фантастика"),
        GenreDto(36, "Исторический"),
        GenreDto(27, "Ужасы"),
        GenreDto(10402, "Музыка"),
        GenreDto(9648, "Мистика"),
        GenreDto(10749, "Романтика"),
        GenreDto(879, "Научная фантастика"),
        GenreDto(53, "Триллер"),
        GenreDto(10752, "Военный"),
        GenreDto(37, "Вестерн"),
        GenreDto(10770, "Рельное ТВ")
    )
}