package ru.knowledge.mtstetaproject.movies.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.knowledge.mtstetaproject.movies.database.dto.ActorDto
import ru.knowledge.mtstetaproject.movies.database.dto.MovieActorCrossRef
import ru.knowledge.mtstetaproject.movies.database.dto.MovieDto

data class MovieWithActors(
    @Embedded val movie: MovieDto,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(MovieActorCrossRef::class)
    )
    val actors: List<ActorDto>
)
