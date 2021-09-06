package ru.knowledge.mtstetaproject.movies.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActors(
    @Embedded val movie: MovieDto,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(MovieActorCrossRef::class)
    )
    val actors: List<ActorDto>
)
