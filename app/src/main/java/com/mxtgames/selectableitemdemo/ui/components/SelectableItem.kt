package com.mxtgames.selectableitemdemo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxtgames.selectableitemdemo.ui.theme.DemoTheme

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    leadingContent: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = {
                    if (enabled) onClick()
                }
            )
            .alpha(if (enabled) 1f else 0.4f)
    ) {
        Card {
            ListItem(
                headlineContent = {
                    Column {
                        TitleText(text = title)
                    }
                },
                leadingContent = leadingContent,
                supportingContent = {
                    if (selected) {
                        SubtitleText(text = description)
                    }
                },
                trailingContent = {
                    if (selected) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = DemoTheme.colors.primary
                        )
                    }
                }
            )
            Divider(
                modifier = Modifier.padding(horizontal = DemoTheme.spaces.M)
            )
        }
    }
}

@Preview
@Composable
fun SelectableItemPreview() {
    DemoTheme {
        LazyColumn {
            items(5) { index ->
                SelectableItem(
                    title = "Title",
                    description = "Description",
                    selected = true
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectableItemSinglePreview() {
    DemoTheme {
        SelectableItem(
            title = "Title",
            description = "Description",
            selected = true
        )
    }
}
