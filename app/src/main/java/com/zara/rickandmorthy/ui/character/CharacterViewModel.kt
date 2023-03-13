package com.zara.rickandmorthy.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zara.domain.model.Character
import com.zara.domain.usecase.GetCharactersUseCase
import com.zara.rickandmorthy.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    BaseViewModel() {

    private var _fetchCharacters = MutableLiveData<List<Character>>()

    val fetchCharacters: LiveData<List<Character>> = _fetchCharacters

    private val listAllCharacters: ArrayList<Character> = arrayListOf()

    fun fetchCharacters() {
        call({
            getCharactersUseCase.fetchCharacters()
        }, onSuccess = {
            listAllCharacters.addAll(it)
            _fetchCharacters.postValue(it)
        })
    }

    fun performQuery(
        query: String,
    ) {
        val filteredList = fetchCharacters.value?.filter {
            it.name.lowercase().startsWith(query)
        }?.toMutableList()
        _fetchCharacters.postValue(
            if (query.isEmpty()) listAllCharacters else filteredList
        )
    }
}