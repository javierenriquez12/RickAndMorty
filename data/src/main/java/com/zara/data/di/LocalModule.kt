package com.zara.data.di

import com.zara.data.source.local.CharacterDataSourceLocal
import com.zara.data.source.local.CharacterDataSourceLocalImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindsCharacterDataSource(
        characterDataSourceLocalImpl: CharacterDataSourceLocalImpl
    ): CharacterDataSourceLocal
}