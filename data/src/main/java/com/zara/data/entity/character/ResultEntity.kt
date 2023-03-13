package com.zara.data.entity.character

data class ResultEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationEntity: LocationEntity,
    val name: String,
    val originEntity: OriginEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)