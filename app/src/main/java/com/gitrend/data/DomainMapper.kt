package com.gitrend.data

import com.gitrend.data.remote.dto.OwnerDto
import com.gitrend.data.remote.dto.RepoDto
import com.gitrend.domain.Owner
import com.gitrend.domain.Repo

internal fun OwnerDto.toDomain() = Owner(
    login = login,
    avatarUrl = avatarUrl
)

internal fun RepoDto.toDomain() = Repo(
    name = name,
    description = description,
    url = url,
    stars = stars,
    language = language,
    owner = owner.toDomain()
)