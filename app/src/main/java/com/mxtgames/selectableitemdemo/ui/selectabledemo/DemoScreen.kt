package com.mxtgames.selectableitemdemo.ui.selectabledemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mxtgames.selectableitemdemo.R
import com.mxtgames.selectableitemdemo.ui.components.IconCotton
import com.mxtgames.selectableitemdemo.ui.components.IconDelicate
import com.mxtgames.selectableitemdemo.ui.components.IconFaq
import com.mxtgames.selectableitemdemo.ui.components.IconLinen
import com.mxtgames.selectableitemdemo.ui.components.IconMixed
import com.mxtgames.selectableitemdemo.ui.components.IconSport
import com.mxtgames.selectableitemdemo.ui.components.IconSynthetics
import com.mxtgames.selectableitemdemo.ui.components.SelectableItem
import com.mxtgames.selectableitemdemo.ui.components.SubtitleText
import com.mxtgames.selectableitemdemo.ui.components.TextButton
import com.mxtgames.selectableitemdemo.ui.components.TitleText
import com.mxtgames.selectableitemdemo.ui.selectabledemo.data.DemoUiState
import com.mxtgames.selectableitemdemo.ui.selectabledemo.data.ItemType
import com.mxtgames.selectableitemdemo.ui.selectabledemo.data.MenuItem
import com.mxtgames.selectableitemdemo.ui.theme.DemoTheme

@Composable
fun DemoScreen(
    demoViewModel: DemoViewModel = hiltViewModel()
) {
    val uiState = demoViewModel.uiState.collectAsState()
    when (val state = uiState.value) {
        DemoUiState.Loading -> {
            LoadingState()
        }

        is DemoUiState.Success -> {
            SuccessState(
                state = state,
                onItemClicked = { item -> demoViewModel.itemClicked(item) }
            )
        }

        is DemoUiState.Error -> {
            ErrorState(
                message = state.message,
                onReloadClicked = { demoViewModel.fetchPrograms() }
            )
        }
    }
}

@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun SuccessState(
    modifier: Modifier = Modifier,
    state: DemoUiState.Success,
    onItemClicked: (selectedItem: MenuItem) -> Unit = {}
) {
    Column(
        modifier = modifier
            .wrapContentHeight(Alignment.Top)
            .padding(
                start = DemoTheme.spaces.M,
                end = DemoTheme.spaces.M,
                top = DemoTheme.spaces.M
            )
    ) {
        Card(
            modifier = Modifier
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(DemoTheme.colors.surface)

            ) {
                items(items = state.items) { item: MenuItem ->
                    SelectableItem(
                        title = item.title,
                        description = item.description,
                        selected = item.selected,
                        enabled = item.enabled,
                        onClick = {
                            onItemClicked(item)
                        },
                        leadingContent = {
                            when (item.itemType) {
                                ItemType.UNKNOWN -> IconFaq()
                                ItemType.COTTON_ECHO,
                                ItemType.COTTONS -> IconCotton()
                                ItemType.SYNTHETICS -> IconSynthetics()
                                ItemType.MIXED -> IconMixed()
                                ItemType.DELICATES -> IconDelicate()
                                ItemType.SPORTS -> IconSport()
                                ItemType.BED_LINEN -> IconLinen()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorState(modifier: Modifier = Modifier, message: String, onReloadClicked: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(text = stringResource(id = R.string.error_general))
            SubtitleText(text = message)
            TextButton(onClick = onReloadClicked, text = stringResource(id = R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DemoScreenPreview() {
    DemoTheme {
        DemoScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DemoScreenLoadingPreview() {
    DemoTheme {
        LoadingState()
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableItemPreview() {
    DemoTheme {
        LazyColumn {
            items(5) { _ ->
                SelectableItem()
            }
        }
    }
}