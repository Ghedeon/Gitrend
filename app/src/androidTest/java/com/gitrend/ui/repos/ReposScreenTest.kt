@file:Suppress("TestFunctionName")

package com.gitrend.ui.repos

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.gitrend.samples.sampleRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@HiltAndroidTest
internal class ReposScreenTest {

    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createComposeRule()

    private val viewModel: ReposViewModel = mock()

    @Test
    fun GIVEN_remote_trending_repo_WHEN_load_repos_screen_THEN_show_repo() {
        // given
        val repo = sampleRepo(name = "Gitrend repo")
        whenever(viewModel.uiState).thenReturn(ReposViewModel.UiState(listOf(repo)))

        // when
        compose.setContent { ReposScreen(viewModel) }

        // then
        compose.onNode(hasText(repo.owner.login)).assertExists()
        compose.onNode(hasText(repo.name)).assertExists()
        compose.onNode(hasText(repo.description)).assertExists()
        compose.onNode(hasText(repo.language!!.name)).assertExists()
        compose.onNode(hasText(repo.stars.toString())).assertExists()
    }
}