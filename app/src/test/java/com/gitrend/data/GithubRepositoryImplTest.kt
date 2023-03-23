@file:OptIn(ExperimentalCoroutinesApi::class)

package com.gitrend.data

import com.gitrend.data.remote.GithubApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


internal class GithubRepositoryImplTest {

    private val api: GithubApi = mock()
    private val repository = GithubRepositoryImpl(api)

    @Test
    fun `GIVEN repository WHEN fetch repos THEN api called`() = runTest {
        // given
        val repo = sampleRepoDto()
        whenever(api.getRepos()).thenReturn(listOf(repo))

        // when
        val repos = repository.getRepos()

        // then
        val expected = repo.toDomain()
        verify(api).getRepos()
        assertThat(repos.single()).isEqualTo(expected)
    }
}