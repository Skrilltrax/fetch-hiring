package dev.skrilltrax.fetch.ui.home

import app.cash.turbine.test
import dev.skrilltrax.fetch.data.FakeItemRepository
import dev.skrilltrax.fetch.data.GroupedSortedListUseCase
import dev.skrilltrax.fetch.fixtures.listItemMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class HomeScreenViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModelInitialSuccessState() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)

        val repository = FakeItemRepository(true)
        val useCase = GroupedSortedListUseCase(repository, dispatcher)
        val viewModel = HomeScreenViewModel(useCase)


        viewModel.uiState.test {
            assertIs<HomeScreenLoadingUiState>(awaitItem())

            val item = awaitItem()
            assertIs<HomeScreenSuccessUiState>(item)

            assertEquals(listItemMap, item.data)
        }

        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModelInitialErrorState() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)

        val repository = FakeItemRepository(false)
        val useCase = GroupedSortedListUseCase(repository, dispatcher)
        val viewModel = HomeScreenViewModel(useCase)


        viewModel.uiState.test {
            assertIs<HomeScreenLoadingUiState>(awaitItem())

            val item = awaitItem()
            assertIs<HomeScreenErrorUiState>(item)

            assertEquals("Error fetching items", item.errorMessage)
        }

        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModelRefreshAction() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)

        val repository = FakeItemRepository(true)
        val useCase = GroupedSortedListUseCase(repository, dispatcher)
        val viewModel = HomeScreenViewModel(useCase)


        viewModel.uiState.test {
            // Initially items were loaded by viewmodel init
            assertIs<HomeScreenLoadingUiState>(awaitItem())
            val item = awaitItem()
            assertIs<HomeScreenSuccessUiState>(item)
            assertEquals(listItemMap, item.data)

            // Refresh data
            viewModel.displayItems()

            // Now items are fetched again by the viewmodel
            assertIs<HomeScreenLoadingUiState>(awaitItem())
            val newItem = awaitItem()
            assertIs<HomeScreenSuccessUiState>(newItem)
            assertEquals(listItemMap, newItem.data)
        }

        Dispatchers.resetMain()
    }
}