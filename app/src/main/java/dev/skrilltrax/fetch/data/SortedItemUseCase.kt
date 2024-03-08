package dev.skrilltrax.fetch.data

import dev.skrilltrax.fetch.di.CpuDispatcher
import dev.skrilltrax.fetch.model.ListItem
import dev.skrilltrax.fetch.model.ListItemDto
import dev.skrilltrax.fetch.utils.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GroupedSortedListUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
    @CpuDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun get() = withContext(dispatcher) {
        return@withContext runSuspendCatching {
            val itemResult = itemRepository.fetchItems()

            // This will always throw
            if (itemResult.isFailure) itemResult.getOrThrow()

            // This will never throw, but if it does, we need to find out where the issue originated
            val items = itemResult.getOrThrow()

            items.filterNullOrEmptyItems().convertDtoToItem().sortItems().groupItems()
        }
    }

    private fun Iterable<ListItemDto>.filterNullOrEmptyItems(): Iterable<ListItemDto> {
        return filter { !it.name.isNullOrEmpty() }
    }

    private fun Iterable<ListItemDto>.convertDtoToItem(): Iterable<ListItem> {
        return map { item -> ListItem(id = item.id, listId = item.listId, name = item.name.orEmpty()) }
    }

    private fun Iterable<ListItem>.sortItems(): Iterable<ListItem> {
        return sortedBy { item -> item.name }.sortedBy { item -> item.listId }
    }

    private fun Iterable<ListItem>.groupItems(): Map<Int, List<ListItem>> {
        return groupBy { item -> item.listId }
    }
}