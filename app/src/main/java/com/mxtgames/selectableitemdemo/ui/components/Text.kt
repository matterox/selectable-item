package com.mxtgames.selectableitemdemo.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mxtgames.selectableitemdemo.ui.theme.DemoTheme

@Composable
fun TitleText(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, color = DemoTheme.colors.onSurface)
}

@Composable
fun SubtitleText(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, color = DemoTheme.colors.onSurfaceVariant)
}