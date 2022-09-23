package mz.co.bilheteira.callmonitor.db.dao

import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.callmonitor.db.entities.Log
import org.junit.Before
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class LogDaoTest {
    private lateinit var log: Log

    @MockK
    private lateinit var logDao: LogDao

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        log = Log(
            id = 1,
            beginning = Calendar.getInstance(Locale.GERMAN).time.toString(),
            duration = "498",
            number = "+120255550203",
            name = "Chris Banes",
            timesQueried = 2
        )

        coEvery { logDao.insertLog(log) } just runs
    }

    @Test
    fun `insert new log`() = runTest {
        // When
        logDao.insertLog(log)

        // Then
        coVerify { logDao.insertLog(log) }
    }


    @Test
    fun `get cached logs to expose on api`() = runTest {
        // Given
        val logs = arrayListOf(
            Log(
                id = 1,
                beginning = Calendar.getInstance(Locale.GERMAN).time.toString(),
                duration = "498",
                number = "+120255550203",
                name = "Chat Chase",
                timesQueried = 100
            ),
            Log(
                id = 2,
                beginning = Calendar.getInstance(Locale.GERMAN).time.toString(),
                duration = "498",
                number = "+120255550203",
                name = "Jake Warton",
                timesQueried = 5
            )
        )

        coEvery { logDao.getLogs() } returns logs

        // When
        val cachedLogs = logDao.getLogs()

        // Then
        assertThat(cachedLogs).isNotEmpty()
    }

    @Test
    fun `delete cached log`() = runTest {
        // Given
        coJustRun { logDao.deleteLog(log) }

        // When
        logDao.deleteLog(log)

        // Then
        coVerify { logDao.deleteLog(log) }
    }

    @Test
    fun `delete cached log and check if it was removed`() = runTest {
        // Given
        val logs = arrayListOf(
            Log(
                id = 1,
                beginning = Calendar.getInstance(Locale.GERMAN).time.toString(),
                duration = "498",
                number = "+120255550203",
                name = "Chat Chase",
                timesQueried = 100
            ),
            Log(
                id = 2,
                beginning = Calendar.getInstance(Locale.GERMAN).time.toString(),
                duration = "498",
                number = "+120255550203",
                name = "Jake Warton",
                timesQueried = 5
            ),
            log
        )

        coJustRun { logDao.deleteLog(log) }

        // When
        logDao.deleteLog(log)

        // Then
        coVerify { logDao.deleteLog(logs.last()) }

        assertThat(logs).contains(log)
    }
}