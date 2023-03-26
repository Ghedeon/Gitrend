package com.gitrend.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ReposResultDto(@SerialName("items") val items: List<RepoDto>)
