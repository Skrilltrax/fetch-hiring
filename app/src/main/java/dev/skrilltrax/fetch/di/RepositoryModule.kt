package dev.skrilltrax.fetch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skrilltrax.fetch.data.FetchItemRepository
import dev.skrilltrax.fetch.data.ItemRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindItemRepository(
        itemRepository: FetchItemRepository
    ): ItemRepository
}