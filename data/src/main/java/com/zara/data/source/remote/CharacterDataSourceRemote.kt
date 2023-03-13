package com.zara.data.source.remote

import com.zara.data.entity.character.CharacterEntity

interface CharacterDataSourceRemote {
    suspend fun fetchCharacters(): Result<CharacterEntity?>
}