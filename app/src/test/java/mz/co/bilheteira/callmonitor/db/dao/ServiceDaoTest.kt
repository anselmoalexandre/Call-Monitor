package mz.co.bilheteira.callmonitor.db.dao

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.callmonitor.db.entities.Service
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ServiceDaoTest {
    @MockK
    private lateinit var serviceDaoMockK: ServiceDao

    private lateinit var services: List<Service>

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        services = arrayListOf(
            Service(id = "1", "status", uri = "http://localhost:8080/status"),
            Service(id = "2", "log", uri = "http://localhost:8080/log")
        )
    }

    @Test
    fun `cache services`() = runTest {
        // Given
        coJustRun { serviceDaoMockK.insertService(services.first()) }

        // When
        serviceDaoMockK.insertService(services.first())

        // Then
        coVerify { serviceDaoMockK.insertService(services.first()) }
    }

    @Test
    fun `get all cached services`() = runTest {
        // Given
        coEvery { serviceDaoMockK.getServices() } returns services

        // When
        val cachedServices = serviceDaoMockK.getServices()

        // Then
        assertThat(cachedServices).containsExactlyElementsIn(services)
    }
}