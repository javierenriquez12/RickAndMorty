package com.zara.data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zara.domain.model.Character
import java.lang.reflect.Type
import javax.inject.Inject

private const val key_preferences_characters = "characters"

class CharacterDataSourceLocalImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    CharacterDataSourceLocal {

    override suspend fun fetchCharacter(): List<Character> {
        val json = sharedPreferences.getString(key_preferences_characters, "")
        val type: Type = object : TypeToken<ArrayList<Character?>?>() {}.type
        return Gson().fromJson<Any>(json, type) as List<Character>
    }

    override suspend fun saveCharacter(character: List<Character>) {
        val prefsEditor = sharedPreferences.edit()
        val json = Gson().toJson(character)
        prefsEditor.putString(key_preferences_characters, json)
        prefsEditor.apply()
    }
}