@file:OptIn(ExperimentalCoroutinesApi::class)

package com.gitrend.ui

import com.gitrend.MainDispatcherRule
import com.gitrend.domain.GithubRepository
import com.gitrend.ui.repos.ReposViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


internal class ReposViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: GithubRepository = mock()

    @Test
    fun `GIVEN view model WHEN init THEN repos are fetched`() = runTest {
        // given
        whenever(repository.getRepos()).thenReturn(emptyList())

        // when
        ReposViewModel(repository)

        // then
        verify(repository).getRepos()
    }
}