package mz.co.bilheteira.callmonitor.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mz.co.bilheteira.callmonitor.db.dao.LogDao
import mz.co.bilheteira.callmonitor.db.dao.ServiceDao
import mz.co.bilheteira.callmonitor.db.dao.StatusDao
import mz.co.bilheteira.callmonitor.db.entities.Log
import mz.co.bilheteira.callmonitor.db.entities.Service
import mz.co.bilheteira.callmonitor.db.entities.Status

@Database(
    entities = [
        Log::class,
        Service::class,
        Status::class
    ],
    version = 2,
    exportSchema = false
)
abstract class CallMonitorDatabase : RoomDatabase() {
    abstract fun getLogDao(): LogDao
    abstract fun getStatusDao(): StatusDao
    abstract fun getServiceDao(): ServiceDao

    companion object {
        const val DATABASE_NAME = "call-monitor.db"
    }
}