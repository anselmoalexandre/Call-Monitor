package mz.co.bilheteira.callmonitor.repository

import mz.co.bilheteira.callmonitor.db.entities.Log
import mz.co.bilheteira.callmonitor.db.entities.Service
import mz.co.bilheteira.callmonitor.db.entities.Status

interface CallMonitorRepository {
    suspend fun getLog(): List<Log>
    suspend fun insertLog(log: Log)
    suspend fun deleteLog(log: Log)

    suspend fun getStatus(): List<Status>
    suspend fun insertStatus(status: Status)
    suspend fun deleteStatus(status: Status)

    suspend fun getService(): List<Service>
    suspend fun insertService(service: Service)
}