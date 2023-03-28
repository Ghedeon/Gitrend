package com.gitrend.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gitrend.R

@Composable
internal fun ErrorScreen(onRetry: () -> Unit = {}) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.retry_anim))
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.error_title),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.padding(top = 18.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.error_subtitle),
            )
        }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18),
            border = BorderStroke(
                ButtonDefaults.outlinedButtonBorder.width,
                MaterialTheme.colorScheme.primary
            ),
            onClick = { onRetry() }) {
            Text(text = stringResource(id = R.string.retry_btn).uppercase())
        }
    }
}