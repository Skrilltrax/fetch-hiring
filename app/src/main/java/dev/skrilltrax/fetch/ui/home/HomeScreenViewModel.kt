package dev.skrilltrax.fetch.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skrilltrax.fetch.data.GroupedSortedListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val groupedSortedListUseCase: GroupedSortedListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState.loadingUiState())
    val uiState = _uiState.asStateFlow()

    init { displayItems() }

    fun displayItems() {
        viewModelScope.launch {
            _uiState.emit(HomeScreenUiState.loadingUiState())

            val result = groupedSortedListUseCase.get()

            if (result.isFailure) {
                _uiState.emit(HomeScreenUiState.errorUiState("Error fetching items"))
            } else {
                val data = result.getOrNull()

                if (data == null) {
                    _uiState.emit(HomeScreenUiState.errorUiState("Error fetching items"))
                } else {
                    _uiState.emit(HomeScreenUiState.successUiState(data))
                }
            }
        }
    }
}