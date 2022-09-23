package mz.co.bilheteira.callmonitor.db.dao

import androidx.room.*
import mz.co.bilheteira.callmonitor.db.entities.Log

@Dao
interface LogDao {
    @Query("SELECT * FROM log")
    suspend fun getLogs(): List<Log>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: Log)

    @Delete
    suspend fun deleteLog(log: Log)
}