package com.gitrend.domain

data class Repo(
    val name: String,
    val description: String,
    val url: String,
    val stars: Int,
    val language: Language?,
    val owner: Owner
)
