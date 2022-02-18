package ru.knowledge.mtstetaproject.movies.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "information_table")
data class TableInfo(
    @PrimaryKey
    @ColumnInfo(name = "table_name")
    val tableName: String,
    @ColumnInfo(name = "last_update")
    val lastUpdate: Date
)
