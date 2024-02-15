package com.mxtgames.selectableitemdemo.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mxtgames.selectableitemdemo.R

@Composable
fun IconFaq(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_faq),
        contentDescription = "Icon FAQ"
    )
}

@Composable
fun IconCotton(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_cotton),
        contentDescription = "Icon Cotton"
    )
}

@Composable
fun IconSynthetics(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_synthetics),
        contentDescription = "Icon Synthetics"
    )
}

@Composable
fun IconMixed(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_mixed),
        contentDescription = "Icon Mixed"
    )
}

@Composable
fun IconDelicate(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_delicates),
        contentDescription = "Icon Delicate"
    )
}

@Composable
fun IconSport(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_sport),
        contentDescription = "Icon Sport"
    )
}

@Composable
fun IconLinen(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_bedding),
        contentDescription = "Icon Linen"
    )
}