package com.gitrend.data

import com.gitrend.data.remote.dto.OwnerDto
import com.gitrend.data.remote.dto.RepoDto
import com.gitrend.domain.Owner
import com.gitrend.domain.Repo

internal fun sampleOwner(login: String = "user", avatarUrl: String = "url") =
    Owner(login = login, avatarUrl = avatarUrl)

internal fun sampleOwnerDto(login: String = "user", avatarUrl: String = "url") =
    OwnerDto(login = login, avatarUrl = avatarUrl)

internal fun sampleRepo(
    name: String = "name",
    description: String = "description",
    url: String = "url",
    stars: String = "stars",
    language: String = "language",
    owner: Owner = sampleOwner()
) = Repo(
    name = "",
    description = "",
    url = "",
    stars = "",
    language = "",
    owner = owner
)

internal fun sampleRepoDto(
    name: String = "name",
    description: String = "description",
    url: String = "url",
    stars: String = "stars",
    language: String = "language",
    owner: OwnerDto = sampleOwnerDto()
) = RepoDto(
    name = name,
    description = description,
    url = url,
    stars = stars,
    language = language,
    owner = owner
)