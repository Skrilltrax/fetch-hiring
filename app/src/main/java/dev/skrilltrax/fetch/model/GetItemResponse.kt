package dev.skrilltrax.fetch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetItemResponse(
	val items: List<GetItemResponseItem?>? = null
)

@JsonClass(generateAdapter = true)
data class GetItemResponseItem(
	@Json(name="listId")
	val listId: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)
