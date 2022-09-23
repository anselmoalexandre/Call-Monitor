package mz.co.bilheteira.callmonitor.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log")
data class Log(
    @PrimaryKey
    val id: Int,
    val beginning: String,
    val duration: String,
    val number: String,
    val name: String,
    val timesQueried: Int
)
