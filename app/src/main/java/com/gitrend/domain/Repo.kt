package com.gitrend.domain

data class Repo(
    val name: String,
    val description: String,
    val url: String,
    val stars: String,
    val language: String,
    val owner: Owner
)
