package mz.co.bilheteira.callmonitor.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mz.co.bilheteira.callmonitor.db.entities.Service

@Dao
interface ServiceDao {
    @Query("SELECT * FROM service")
    suspend fun getServices():List<Service>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: Service)
}