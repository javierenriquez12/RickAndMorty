package com.zara.data.source.local

import com.zara.domain.model.Character

interface CharacterDataSourceLocal {
    suspend fun fetchCharacter(): List<Character>
    suspend fun saveCharacter(character: List<Character>)
}