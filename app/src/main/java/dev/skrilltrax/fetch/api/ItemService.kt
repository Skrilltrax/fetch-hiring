package dev.skrilltrax.fetch.api

import dev.skrilltrax.fetch.model.ListItemDto
import retrofit2.http.GET

interface ItemService {

    @GET("/hiring.json")
    suspend fun getItems(): List<ListItemDto>
}