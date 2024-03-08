package dev.skrilltrax.fetch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListItemDto(
    @Json(name = "listId")
    val listId: Int,

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String? = null
)
