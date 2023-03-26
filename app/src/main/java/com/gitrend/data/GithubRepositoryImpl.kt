package com.gitrend.data

import com.gitrend.data.local.LanguagesDataSource
import com.gitrend.data.remote.GithubApi
import com.gitrend.domain.GithubRepository
import com.gitrend.domain.Repo
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi,
    private val languages: LanguagesDataSource
) : GithubRepository {

    override suspend fun getRepos(): List<Repo> {
        val colors = languages.getColors()
        return api.getRepos().items.map { repo -> repo.toDomain(colors) }
    }
}