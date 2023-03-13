package com.zara.domain.model

data class Character(
    val created: String? = null,
    val episode: List<String>? = null,
    val gender: String? = null,
    val id: Int,
    val image: String,
    val name: String,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String
)
