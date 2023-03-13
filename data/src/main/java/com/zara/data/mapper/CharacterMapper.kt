package com.zara.data.mapper

import com.zara.data.entity.character.ResultEntity
import com.zara.domain.model.Character

object CharacterMapper {
    fun ResultEntity.toDomain() = Character(
        id = this.id,
        name = this.name,
        image = this.image,
        url = this.url
    )
}