package com.gitrend.samples

import com.gitrend.domain.Language
import com.gitrend.domain.Owner
import com.gitrend.domain.Repo

fun sampleRepo(
    name: String = "name",
    description: String = "description",
    url: String = "url",
    stars: Int = 42,
    language: Language? = sampleLanguage(),
    owner: Owner = sampleOwner(),
) = Repo(
    name = name,
    description = description,
    url = url,
    stars = stars,
    language = language,
    owner = owner
)

fun sampleOwner(login: String = "login", avatarUrl: String = "avatarUrl") =
    Owner(login = login, avatarUrl = avatarUrl)

fun sampleLanguage(language: String = "Kotlin", color: String? = "#A97BFF") =
    Language(name = language, color = color)