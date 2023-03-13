package com.zara.data.source.remote

import com.zara.data.entity.character.CharacterEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/character")
    suspend fun fetchCharacters(): Response<CharacterEntity>
}