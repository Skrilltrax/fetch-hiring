package dev.skrilltrax.fetch.data

import dev.skrilltrax.fetch.api.ItemService
import dev.skrilltrax.fetch.di.IoDispatcher
import dev.skrilltrax.fetch.model.ListItemDto
import dev.skrilltrax.fetch.utils.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemService: ItemService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun fetchItems(): Result<List<ListItemDto>> = withContext(dispatcher) {
        return@withContext runSuspendCatching { itemService.getItems() }
    }
}