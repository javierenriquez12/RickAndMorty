package com.zara.data.di

import com.zara.data.repository.CharacterRepositoryImpl
import com.zara.data.source.remote.CharacterDataSourceRemote
import com.zara.data.source.remote.CharacterDataSourceRemoteImpl
import com.zara.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetModule {

    @Binds
    abstract fun bindsCharacterDataSource(
        characterDataSourceRemoteImpl: CharacterDataSourceRemoteImpl
    ): CharacterDataSourceRemote


    @Binds
    abstract fun bindsCharacterRepository(
        characterRepository: CharacterRepositoryImpl
    ): CharacterRepository
}