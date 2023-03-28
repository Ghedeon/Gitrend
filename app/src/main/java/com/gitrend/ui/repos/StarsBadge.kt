package com.gitrend.ui.repos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gitrend.R
import com.gitrend.ui.theme.starYellow
import java.text.DecimalFormat

@Composable
internal fun StarsBadge(modifier: Modifier = Modifier, stars: Int) {
    val formatted = if (stars < 1000) "$stars" else "${df.format(stars.toFloat() / 1000)}k"
    Row(
        modifier = modifier.padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 6.dp)
                .size(16.dp),
            painter = painterResource(id = R.drawable.baseline_star_24),
            tint = starYellow,
            contentDescription = null
        )
        Text(text = formatted)
    }
}

private val df = DecimalFormat("##.#")

@Preview(showBackground = true)
@Composable
private fun StarsPreview() {
    StarsBadge(stars = 12345)
}