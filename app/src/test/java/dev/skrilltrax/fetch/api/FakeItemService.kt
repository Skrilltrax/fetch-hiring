package dev.skrilltrax.fetch.api

import dev.skrilltrax.fetch.fixtures.listItemDtoWithNullAndEmptyList
import dev.skrilltrax.fetch.model.ListItemDto
import java.lang.IllegalStateException

class FakeItemService(private val successResponse: Boolean) : ItemService {

    override suspend fun getItems(): List<ListItemDto> {
        if (successResponse) return listItemDtoWithNullAndEmptyList

        throw IllegalStateException()
    }
}