package com.zara.domain.repository

import com.zara.domain.model.Character

interface CharacterRepository {
    suspend fun fetchCharacters(): Result<List<Character>>
    suspend fun fetchDetailCharacter(characterId: Int): Result<Character>
}