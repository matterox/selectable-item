package com.mxtgames.selectableitemdemo.ui.selectabledemo.data

import kotlinx.collections.immutable.ImmutableList

sealed class DemoUiState {
    data object Loading: DemoUiState()
    data class Error(val message: String): DemoUiState()
    data class Success(val items: ImmutableList<MenuItem>): DemoUiState()
}

data class MenuItem(
    val title: String = "Name",
    val description: String = "Description",
    val itemType: ItemType = ItemType.UNKNOWN,
    val selected: Boolean = false,
    val enabled: Boolean = true,
)
