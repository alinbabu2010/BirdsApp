package model

import kotlinx.serialization.Serializable

@Serializable
data class BirdInfo(
    val author: String,
    val category: String,
    val path: String
)