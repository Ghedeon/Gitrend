package com.gitrend.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OwnerDto(
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)
