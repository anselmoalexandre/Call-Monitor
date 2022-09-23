package mz.co.bilheteira.callmonitor.db.dao

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.callmonitor.db.entities.Root
import org.junit.Before
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class RootDaoTest {
    @MockK
    private lateinit var rootDaoMockK: RootDao

    private lateinit var root: Root

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        root = Root(start = Calendar.getInstance(Locale.GERMAN).time.toString(), id = "1")
    }

    @Test
    fun `cache root services`() = runTest {
        // Given
        coJustRun { rootDaoMockK.insertRoot(root) }

        // When
        rootDaoMockK.insertRoot(root)

        // Then
        coVerify { rootDaoMockK.insertRoot(root) }
    }

    @Test
    fun `get all cached roots services`() = runTest {
        // Given
        coEvery { rootDaoMockK.getRoot() } returns root

        // When
        val cachedRoot = rootDaoMockK.getRoot()

        // Then
        assertThat(cachedRoot).isNotNull()
    }
}