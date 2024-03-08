package dev.skrilltrax.fetch.ui.home

import dev.skrilltrax.fetch.model.ListItem

data class HomeScreenUiState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: Map<Int, List<ListItem>> = emptyMap(),
    val errorMessage: String = ""
) {
    companion object  {
        public fun loadingUiState(): HomeScreenUiState {
            return HomeScreenUiState(isLoading = true)
        }

        public fun errorUiState(errorMessage: String): HomeScreenUiState {
            return HomeScreenUiState(isError = false, errorMessage = errorMessage)
        }

        public fun successUiState(data: Map<Int, List<ListItem>>): HomeScreenUiState {
            return HomeScreenUiState(isSuccess = true, data = data)
        }
    }
}
