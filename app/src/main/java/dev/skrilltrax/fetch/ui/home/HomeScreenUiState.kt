package dev.skrilltrax.fetch.ui.home

import dev.skrilltrax.fetch.model.ListItem

sealed class HomeScreenUiState

class HomeScreenSuccessUiState(val data: Map<Int, List<ListItem>>) : HomeScreenUiState()
class HomeScreenErrorUiState(val errorMessage: String) : HomeScreenUiState()
data object HomeScreenLoadingUiState : HomeScreenUiState()
