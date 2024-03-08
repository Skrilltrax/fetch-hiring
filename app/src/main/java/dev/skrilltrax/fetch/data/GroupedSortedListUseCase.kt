package dev.skrilltrax.fetch.data

import dev.skrilltrax.fetch.di.CpuDispatcher
import dev.skrilltrax.fetch.model.ListItem
import dev.skrilltrax.fetch.utils.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GroupedSortedListUseCase @Inject constructor(
    private val fetchItemRepository: ItemRepository,
    @CpuDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun get() = withContext(dispatcher) {
        return@withContext runSuspendCatching {
            val itemResult = fetchItemRepository.fetchItems()

            // This will always throw but we are running it inside suspend catching
            if (itemResult.isFailure) itemResult.getOrThrow()

            // This should never throw, but if it does, we need to find out where the issue originated
            val items = itemResult.getOrThrow()

            // Apply the transformations to the DTO
            return@runSuspendCatching items
                .asSequence()
                .filter { !it.name.isNullOrEmpty() } // Filter null or empty items
                .map { item -> ListItem(id = item.id, listId = item.listId, name = item.name!!) } // Map DTO to application model
                .sortedBy { item -> item.name.split(" ")[1].toInt() } // Sort items by name
                .sortedBy { item -> item.listId } // Sort items by listId
                .groupBy { item -> item.listId } // Group items by listId
        }
    }
}