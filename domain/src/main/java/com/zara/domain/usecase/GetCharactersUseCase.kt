package com.zara.domain.usecase

import com.zara.domain.model.Character
import com.zara.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun fetchCharacters(): Result<List<Character>> {
        return characterRepository.fetchCharacters()
    }
}