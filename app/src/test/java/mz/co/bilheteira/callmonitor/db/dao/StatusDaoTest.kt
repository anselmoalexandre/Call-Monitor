package mz.co.bilheteira.callmonitor.db.dao

import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.callmonitor.db.entities.Status
import org.junit.Before
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class StatusDaoTest {
    @MockK
    private lateinit var statusDaoMock: StatusDao

    private val status = Status(
        id = 1,
        ongoing = true,
        number = "+258845479011",
        name = "Anselmo Alexandre"
    )

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)

        coEvery { statusDaoMock.insertStatus(status) } just Runs
    }

    @Test
    fun `cache status`() = runTest {
        // When
        statusDaoMock.insertStatus(status)

        // Then
        coVerify { statusDaoMock.insertStatus(status) }
    }

    @Test
    fun `get cached status`() = runTest {
        // Given
        coEvery { statusDaoMock.getStatus() } returns arrayListOf(status)

        // When
        val cachedStatus = statusDaoMock.getStatus()

        // Then
        assertThat(cachedStatus).isNotEmpty()
    }

    @Test
    fun `delete cached status`() = runTest {
        // Given
        coJustRun { statusDaoMock.deleteStatus(status) }

        // When
        statusDaoMock.deleteStatus(status)

        // Then
        coVerify { statusDaoMock.deleteStatus(status) }
    }
}