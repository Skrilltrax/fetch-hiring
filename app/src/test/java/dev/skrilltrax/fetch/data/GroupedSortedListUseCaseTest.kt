package dev.skrilltrax.fetch.data

import dev.skrilltrax.fetch.fixtures.listItemMap
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class GroupedSortedListUseCaseTest {

    @Test
    fun testUseCaseSuccess() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = FakeItemRepository(true)
        val useCase = GroupedSortedListUseCase(repository, dispatcher)

        val result = useCase.get()
        assertTrue(result.isSuccess)

        val actual = result.getOrThrow()
        assertEquals(listItemMap, actual)
    }

    @Test
    fun testUseCaseFailure() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = FakeItemRepository(false)
        val useCase = GroupedSortedListUseCase(repository, dispatcher)

        val result = useCase.get()
        assertTrue(result.isFailure)

        val actual = result.exceptionOrNull()
        assertIs<IllegalStateException>(actual)
    }
}