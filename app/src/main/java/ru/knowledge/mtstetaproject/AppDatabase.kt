package ru.knowledge.mtstetaproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.knowledge.mtstetaproject.movies.database.Converters
import ru.knowledge.mtstetaproject.movies.database.MovieDao
import ru.knowledge.mtstetaproject.movies.database.dto.*

@Database(entities = [
        MovieDto::class,
        ActorDto::class,
        GenreDto::class,
        MovieActorCrossRef::class,
        TableInfo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

        abstract fun movieDao(): MovieDao

        companion object {
                private const val DATABASE_NAME = "Movies.db"
                private var INSTANCE: AppDatabase? = null

                fun getDatabase(context: Context): AppDatabase {
                        return INSTANCE ?: synchronized(this) {
                                val instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        AppDatabase::class.java,
                                        DATABASE_NAME
                                ).fallbackToDestructiveMigration().build()
                                INSTANCE = instance
                                instance
                        }
                }
        }
}