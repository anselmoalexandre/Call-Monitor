package mz.co.bilheteira.callmonitor.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service")
data class Service(
    @PrimaryKey
    val id: String,
    val name: String,
    val uri: String
)
