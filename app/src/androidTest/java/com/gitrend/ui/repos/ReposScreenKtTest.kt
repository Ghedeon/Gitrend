@file:Suppress("TestFunctionName")

package com.gitrend.ui.repos

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.gitrend.samples.sampleRepo
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@HiltAndroidTest
class ReposScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val rule = createComposeRule()

    @BindValue
    @JvmField
    val viewModel: ReposViewModel = mock()

    @Test
    fun GIVEN_remote_trending_repo_WHEN_load_repos_screen_THEN_show_repo() {
        // given
        val repo = sampleRepo(name = "Gitrend repo")
        whenever(viewModel.uiState).thenReturn(ReposViewModel.UiState(listOf(repo)))

        // when
        rule.setContent { ReposScreen(viewModel) }

        // then
        rule.onNode(hasText(repo.owner.login)).assertExists()
        rule.onNode(hasText(repo.name)).assertExists()
        rule.onNode(hasText(repo.description)).assertExists()
        rule.onNode(hasText(repo.language!!.name)).assertExists()
        rule.onNode(hasText(repo.stars.toString())).assertExists()
    }
}