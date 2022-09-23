package mz.co.bilheteira.callmonitor.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.callmonitor.db.CallMonitorDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CallMonitorDatabase::class.java,
        CallMonitorDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideLogDao(db: CallMonitorDatabase) = db.getLogDao()

    @Provides
    fun provideRootDao(db: CallMonitorDatabase) = db.getRootDao()

    @Provides
    fun provideServiceDao(db: CallMonitorDatabase) = db.getServiceDao()

    @Provides
    fun provideStatusDao(db: CallMonitorDatabase) = db.getStatusDao()
}