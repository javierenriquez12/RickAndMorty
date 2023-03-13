package com.zara.domain.usecase

import com.zara.domain.model.Character
import com.zara.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun fetchCharacterDetail(characterId: Int): Result<Character> {
        return characterRepository.fetchDetailCharacter(characterId)
    }
}