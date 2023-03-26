@file:Suppress("TestFunctionName")
@file:OptIn(ExperimentalCoroutinesApi::class)

package com.gitrend.ui.repos

import com.gitrend.MockWebServerRule
import com.gitrend.domain.GithubRepository
import com.gitrend.domain.Language
import com.gitrend.domain.Owner
import com.gitrend.samples.sampleRepo
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class ReposViewModelTest {

    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val server = MockWebServerRule()

    @Inject
    lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        hilt.inject()
    }

    @Test
    fun GIVEN_remote_repos_WHEN_repository_call_THEN_fetch_remote() = runTest {
        // given
        server.enqueue("repos-200.json", 200)
        val expected = sampleRepo(
            name = "kotlin",
            description = "The Kotlin Programming Language. ",
            url = "https://api.github.com/repos/JetBrains/kotlin",
            stars = 44200,
            language = Language(name = "Kotlin", color = "#A97BFF"),
            owner = Owner(
                login = "JetBrains",
                avatarUrl = "https://avatars.githubusercontent.com/u/878437?v=4"
            )
        )

        // when
        val result = repository.getRepos()

        // then
        assertThat(result.first()).isEqualTo(expected)
    }
}