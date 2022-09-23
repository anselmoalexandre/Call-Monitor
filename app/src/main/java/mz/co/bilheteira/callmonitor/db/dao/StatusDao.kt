package mz.co.bilheteira.callmonitor.db.dao

import androidx.room.*
import mz.co.bilheteira.callmonitor.db.entities.Status

@Dao
interface StatusDao {
    @Query("SELECT * FROM status")
    suspend fun getStatus() : List<Status>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatus(status: Status)

    @Delete
    suspend fun deleteStatus(status: Status)
}