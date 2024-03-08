package dev.skrilltrax.fetch.fixtures

import dev.skrilltrax.fetch.model.ListItem
import dev.skrilltrax.fetch.model.ListItemDto

val listItemDtoWithNullAndEmptyList = listOf(
    ListItemDto(id = 1, listId = 1, name = "Item 1"),
    ListItemDto(id = 2, listId = 2, name = null),
    ListItemDto(id = 3, listId = 3, name = "Item 3"),
    ListItemDto(id = 4, listId = 1, name = "Item 4"),
    ListItemDto(id = 5, listId = 2, name = "Item 5"),
    ListItemDto(id = 6, listId = 3, name = ""),
)

val listItemMap = mapOf(
    1 to listOf(ListItem(id = 1, listId = 1, name = "Item 1"), ListItem(id = 4, listId = 1, name = "Item 4")),
    2 to listOf(ListItem(id = 5, listId = 2, name = "Item 5")),
    3 to listOf(ListItem(id = 3, listId = 3, name = "Item 3")),
)
