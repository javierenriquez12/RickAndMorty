package com.zara.data.repository

import com.zara.data.mapper.CharacterMapper.toDomain
import com.zara.data.source.local.CharacterDataSourceLocal
import com.zara.data.source.remote.CharacterDataSourceRemote
import com.zara.domain.model.Character
import com.zara.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSourceRemote: CharacterDataSourceRemote,
    private val characterDataSourceLocal: CharacterDataSourceLocal
) : CharacterRepository {

    private val cachedList = mutableListOf<Character>()


    override suspend fun fetchCharacters(): Result<List<Character>> {
        return characterDataSourceRemote.fetchCharacters().fold(
            onSuccess = {
                if (it != null) {
                    val listCharacters =
                        it.results.map { resultEntity -> resultEntity.toDomain() }.toMutableList()
                    characterDataSourceLocal.saveCharacter(listCharacters)
                    Result.success(listCharacters)
                } else {
                    Result.success(cachedList)
                }
            },
            onFailure = {
                Result.success(cachedList)
            }
        )
    }

    override suspend fun fetchDetailCharacter(characterId: Int): Result<Character> {
        return characterDataSourceLocal.fetchCharacter().find { it.id == characterId }?.let {
            Result.success(
                it
            )
        } ?: run { Result.failure(Exception("An error occurred when get new detail")) }
    }


}