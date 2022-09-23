package mz.co.bilheteira.callmonitor.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
data class Status(
    @PrimaryKey
    val id: Int,
    val ongoing: Boolean,
    val number: String,
    val name: String
)
