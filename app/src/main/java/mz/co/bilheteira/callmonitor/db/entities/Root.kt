package mz.co.bilheteira.callmonitor.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "root",
    primaryKeys = ["start", "id"],
    foreignKeys = [
        ForeignKey(
            entity = Service::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class Root(
    val start: String,
    val id:String
)
