@file:OptIn(ExperimentalMaterial3Api::class)

package com.gitrend.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gitrend.ui.theme.lightGrey
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
internal fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        LoadingItem()
        ListDivider()
        LoadingItem()
        ListDivider()
        LoadingItem()
        ListDivider()
    }
}

@Composable
private fun LoadingItem() {
    ListItem(
        modifier = Modifier.defaultMinSize(minHeight = 80.dp),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = true,
                        color = lightGrey,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                    ),
            )
        },
        overlineText = {
            Box(
                modifier = Modifier
                    .size(100.dp, 18.dp)
                    .placeholder(
                        visible = true,
                        color = lightGrey,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                    ),
            )
        },
        headlineText = {
            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .height(18.dp)
                    .placeholder(
                        visible = true,
                        color = lightGrey,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                    ),
            )
        },
    )
}

@Composable
internal fun ListDivider() {
    Divider(
        modifier = Modifier.padding(start = 18.dp),
        color = lightGrey,
        thickness = 2.dp
    )
}

@Preview
@Composable
fun LoadingPreview() {
    LoadingScreen()
}