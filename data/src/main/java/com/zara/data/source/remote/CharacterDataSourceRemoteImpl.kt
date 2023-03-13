package com.zara.data.source.remote

import com.zara.data.entity.character.CharacterEntity
import com.zara.data.entity.error.RequestException
import java.net.HttpURLConnection
import javax.inject.Inject

class CharacterDataSourceRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterDataSourceRemote {

    override suspend fun fetchCharacters(): Result<CharacterEntity?> {
        val apiResponse = apiService.fetchCharacters()
        return if (apiResponse.isSuccessful) {
            Result.success(apiResponse.body())
        } else {
            Result.failure(
                RequestException(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "An error occurred!"
                )
            )
        }
    }
}