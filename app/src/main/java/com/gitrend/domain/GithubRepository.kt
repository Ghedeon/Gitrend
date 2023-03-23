package com.gitrend.domain

interface GithubRepository {
    suspend fun getRepos(): List<Repo>
}