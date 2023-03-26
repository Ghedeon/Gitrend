package com.gitrend.ui.repos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.gitrend.domain.Language

@Composable
fun LanguageBadge(modifier: Modifier = Modifier, language: Language) {
    Row(
        modifier = modifier.padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        language.color?.let { color ->
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(9.dp)
                    .clip(CircleShape)
                    .background(Color(color.toColorInt()))
            )
        }
        Text(text = language.name)
    }
}

@Preview
@Composable
private fun LanguagePreview() {
    LanguageBadge(language = Language("Kotlin", "#A97BFF"))
}