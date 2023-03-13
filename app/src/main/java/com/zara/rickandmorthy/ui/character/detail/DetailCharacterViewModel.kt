package com.zara.rickandmorthy.ui.character.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zara.domain.model.Character
import com.zara.domain.usecase.GetCharacterDetailUseCase
import com.zara.rickandmorthy.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val useCase: GetCharacterDetailUseCase
) : BaseViewModel() {

    private val _showSmartMode = MutableLiveData(true)
    val showSmartMode: LiveData<Boolean> = _showSmartMode

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun getNewsById(characterId: Int) {
        call({
            useCase.fetchCharacterDetail(characterId)
        }, onSuccess = {
            _character.postValue(it)
        })
    }
}
