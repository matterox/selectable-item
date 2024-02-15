package com.mxtgames.selectableitemdemo.ui.selectabledemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxtgames.selectableitemdemo.repository.ProgramsRepository
import com.mxtgames.selectableitemdemo.ui.selectabledemo.data.DemoUiState
import com.mxtgames.selectableitemdemo.ui.selectabledemo.data.MenuItem
import com.mxtgames.selectableitemdemo.ui.selectabledemo.data.toItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val programsRepository: ProgramsRepository,
): ViewModel() {
    private val _uiState: MutableStateFlow<DemoUiState> = MutableStateFlow(DemoUiState.Loading)
    val uiState: StateFlow<DemoUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DemoUiState.Loading,
    )

    init {
        fetchPrograms()
        viewModelScope.launch {
            programsRepository.observePrograms().collectLatest { result ->
                result.programs?.let { data ->
                    _uiState.value = DemoUiState.Success(
                        items = data.map { item ->
                            MenuItem(
                                title = item.title,
                                description = item.subtitle,
                                selected = item.selected,
                                enabled = true,
                                itemType = item.programType.toItemType()
                            )
                        }.toImmutableList()
                    )
                }
            }
        }
    }

    fun fetchPrograms() {
        viewModelScope.launch {
            _uiState.value = DemoUiState.Loading
            programsRepository.updatePrograms().fold({ error ->
                _uiState.value = DemoUiState.Error(error.message)
            }, {})
        }
    }

    fun itemClicked(selectedItem: MenuItem) {
        viewModelScope.launch {
            programsRepository.setProgram(selectedItem.itemType.value)
        }
    }
}