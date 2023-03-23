package com.gitrend.data.remote

import com.gitrend.data.remote.dto.RepoDto
import retrofit2.http.GET

internal interface GithubApi {
    @GET("search/repositories?q=language+sort:stars")
    suspend fun getRepos(): List<RepoDto>
}