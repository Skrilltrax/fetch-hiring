package dev.skrilltrax.fetch.data

import dev.skrilltrax.fetch.fixtures.listItemDtoWithNullAndEmptyList
import dev.skrilltrax.fetch.model.ListItemDto
import java.lang.IllegalStateException

class FakeItemRepository(private val successResponse: Boolean) : ItemRepository {

    override suspend fun fetchItems(): Result<List<ListItemDto>> {
        return runCatching {
            if (successResponse) listItemDtoWithNullAndEmptyList
            else throw IllegalStateException()
        }
    }
}