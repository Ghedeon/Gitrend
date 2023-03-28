@file:Suppress("TestFunctionName")

package com.gitrend.ui.repos

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gitrend.R
import com.gitrend.samples.sampleRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@HiltAndroidTest
internal class ReposScreenTest {

    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val viewModel: ReposViewModel = mock()

    @Test
    fun GIVEN_remote_repos_WHEN_load_repos_screen_THEN_show_repo() {
        // given
        val repo = sampleRepo(name = "Gitrend repo")
        whenever(viewModel.uiState).thenReturn(ReposViewModel.UiState.Data(listOf(repo)))

        // when
        compose.setContent { ReposScreen(viewModel) }

        // then
        compose.onNodeWithText(repo.owner.login).assertIsDisplayed()
        compose.onNodeWithText(repo.name).assertIsDisplayed()
        compose.onNodeWithText(repo.description).assertIsDisplayed()
        compose.onNodeWithText(repo.language!!.name).assertIsDisplayed()
        compose.onNodeWithText(repo.stars.toString()).assertIsDisplayed()
    }

    @Test
    fun GIVEN_load_repos_error_WHEN_open_repos_screen_THEN_show_error() {
        // given
        whenever(viewModel.uiState).thenReturn(ReposViewModel.UiState.Error)

        // when
        compose.setContent { ReposScreen(viewModel) }

        // then
        compose.onNodeWithText(compose.activity.getString(R.string.error_title))
            .assertIsDisplayed()
        compose.onNodeWithText(compose.activity.getString(R.string.error_subtitle))
            .assertIsDisplayed()
        compose.onNodeWithText(compose.activity.getString(R.string.retry_btn), ignoreCase = true)
            .assertIsDisplayed()
    }

    @Test
    fun GIVEN_error_WHEN_retry_clicked_THEN_load_repos() {
        // given
        whenever(viewModel.uiState).thenReturn(ReposViewModel.UiState.Error)
        compose.setContent { ReposScreen(viewModel) }

        // when
        compose.onNodeWithText(compose.activity.getString(R.string.retry_btn), ignoreCase = true)
            .performClick()

        // then
        verify(viewModel).loadRepos()
    }
}