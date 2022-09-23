package mz.co.bilheteira.callmonitor.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mz.co.bilheteira.callmonitor.db.entities.Root

@Dao
interface RootDao {
    @Query("SELECT * FROM root " +
            "INNER JOIN service " +
            "ON root.id = service.id")
    suspend fun getRoot(): Root?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoot(root: Root)
}