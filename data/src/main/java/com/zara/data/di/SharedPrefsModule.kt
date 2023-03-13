package com.zara.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val nameSharedPreferences = "RickAndMortyPref"

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefsModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(nameSharedPreferences, Context.MODE_PRIVATE)
}