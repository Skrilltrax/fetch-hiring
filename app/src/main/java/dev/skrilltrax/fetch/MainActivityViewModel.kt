package dev.skrilltrax.fetch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skrilltrax.fetch.data.ItemRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    fun displayItems() {
        viewModelScope.launch {
            val items = itemRepository.fetchItems().getOrNull()

            println(items)
        }
    }
}