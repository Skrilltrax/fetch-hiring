package dev.skrilltrax.fetch.data

import dev.skrilltrax.fetch.api.FakeItemService
import dev.skrilltrax.fetch.fixtures.listItemDtoWithNullAndEmptyList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class ItemRepositoryTest {

    @Test
    fun testFetchItemSuccess() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val itemService = FakeItemService(successResponse = true)
        val repository = FetchItemRepository(itemService, dispatcher)

        val result = repository.fetchItems()
        assertTrue(result.isSuccess)

        val actual = result.getOrThrow()
        assertEquals(listItemDtoWithNullAndEmptyList, actual)
    }

    @Test
    fun testFetchItemFailure() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        // Simulating network error here
        val itemService = FakeItemService(successResponse = false)
        val repository = FetchItemRepository(itemService, dispatcher)

        val result = repository.fetchItems()
        assertTrue(result.isFailure)

        val actual = result.exceptionOrNull()
        assertIs<IllegalStateException>(actual)
    }
}