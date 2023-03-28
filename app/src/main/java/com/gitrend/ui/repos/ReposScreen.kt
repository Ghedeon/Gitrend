@file:OptIn(ExperimentalMaterial3Api::class)

package com.gitrend.ui.repos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gitrend.domain.Repo
import com.gitrend.ui.common.ErrorScreen
import com.gitrend.ui.common.ListDivider
import com.gitrend.ui.common.LoadingScreen
import com.gitrend.ui.repos.ReposViewModel.UiState

@Composable
internal fun ReposScreen(viewModel: ReposViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() }
    ) { paddings ->
        Surface(Modifier.padding(paddings)) {
            when (val uiState = viewModel.uiState) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Data -> Content(uiState = uiState)
                is UiState.Error -> ErrorScreen(onRetry = {
                    viewModel.loadRepos()
                })
            }
        }
    }
}

@Composable
private fun Content(modifier: Modifier = Modifier, uiState: UiState.Data) {
    LazyColumn(
        modifier = modifier
    ) {
        items(uiState.repos) { repo -> RepoItem(repo) }
    }
}

@Composable
private fun RepoItem(repo: Repo) {
    ListItem(
        modifier = Modifier.defaultMinSize(minHeight = 120.dp),
        leadingContent = {
            Box(modifier = Modifier.fillMaxHeight()) {
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp)
                        .clip(CircleShape),
                    model = repo.owner.avatarUrl,
                    contentDescription = null
                )
            }
        },
        overlineText = {
            Text(
                text = repo.owner.login,
                fontSize = 14.sp
            )
        },
        headlineText = {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = repo.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        },
        supportingText = {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = repo.description,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Row(modifier = Modifier.padding(top = 6.dp)) {
                repo.language?.let { language ->
                    LanguageBadge(
                        modifier = Modifier.padding(end = 12.dp),
                        language = language
                    )
                }
                StarsBadge(stars = repo.stars)
            }
        }
    )
    ListDivider()
}