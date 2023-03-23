package com.gitrend.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RepoDto(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("url")
    val url: String,
    @SerialName("stars")
    val stars: String,
    @SerialName("language")
    val language: String,
    @SerialName("owner")
    val owner: OwnerDto
)
