package mz.co.bilheteira.callmonitor.repository

import mz.co.bilheteira.callmonitor.db.dao.LogDao
import mz.co.bilheteira.callmonitor.db.dao.RootDao
import mz.co.bilheteira.callmonitor.db.dao.ServiceDao
import mz.co.bilheteira.callmonitor.db.dao.StatusDao
import mz.co.bilheteira.callmonitor.db.entities.Log
import mz.co.bilheteira.callmonitor.db.entities.Root
import mz.co.bilheteira.callmonitor.db.entities.Service
import mz.co.bilheteira.callmonitor.db.entities.Status
import javax.inject.Inject

class CallMonitorRepositoryImpl @Inject constructor(
    private val logDao: LogDao,
    private val rootDao: RootDao,
    private val serviceDao: ServiceDao,
    private val statusDao: StatusDao
) : CallMonitorRepository {
    override suspend fun getLog(): List<Log> = logDao.getLogs()

    override suspend fun insertLog(log: Log) = logDao.insertLog(log)

    override suspend fun deleteLog(log: Log) = logDao.deleteLog(log)

    override suspend fun getStatus(): List<Status> = statusDao.getStatus()

    override suspend fun insertStatus(status: Status) = statusDao.insertStatus(status)

    override suspend fun deleteStatus(status: Status) = statusDao.deleteStatus(status)

    override suspend fun getRoot(): Root? = rootDao.getRoot()

    override suspend fun getService(): List<Service> =  serviceDao.getServices()

    override suspend fun insertService(service: Service) = serviceDao.insertService(service)
}