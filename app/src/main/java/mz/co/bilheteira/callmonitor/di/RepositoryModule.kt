package mz.co.bilheteira.callmonitor.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mz.co.bilheteira.callmonitor.db.dao.LogDao
import mz.co.bilheteira.callmonitor.db.dao.RootDao
import mz.co.bilheteira.callmonitor.db.dao.ServiceDao
import mz.co.bilheteira.callmonitor.db.dao.StatusDao
import mz.co.bilheteira.callmonitor.repository.CallMonitorRepository
import mz.co.bilheteira.callmonitor.repository.CallMonitorRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(
        logDao: LogDao,
        rootDao: RootDao,
        serviceDao: ServiceDao,
        statusDao: StatusDao
    ): CallMonitorRepository = CallMonitorRepositoryImpl(logDao, rootDao, serviceDao, statusDao)
}