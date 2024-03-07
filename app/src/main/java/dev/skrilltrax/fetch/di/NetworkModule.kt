package dev.skrilltrax.fetch.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skrilltrax.fetch.api.ItemService
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Reusable
    fun provideApiService(converterFactory: Converter.Factory): ItemService {
        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(converterFactory)
            .build()
            .create<ItemService>()
    }

    @Provides
    @Reusable
    fun provideMoshiConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }
}