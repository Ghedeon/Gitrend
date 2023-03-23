package com.gitrend.data

import com.gitrend.data.remote.GithubApi
import com.gitrend.data.remote.dto.RepoDto
import com.gitrend.domain.GithubRepository
import com.gitrend.domain.Repo
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(private val api: GithubApi) :
    GithubRepository {

    override suspend fun getRepos(): List<Repo> =
        api.getRepos().map(RepoDto::toDomain)
}