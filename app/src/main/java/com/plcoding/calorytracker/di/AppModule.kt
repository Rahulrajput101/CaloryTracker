package com.plcoding.calorytracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.android.material.tabs.TabLayout.Mode
import com.plcoding.core.data.preferences.DefaultPreferences
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.domain.use_case.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
     app: Application
    ) : SharedPreferences{
        return  app.getSharedPreferences("shared_pref",MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences) : Preference {
        return DefaultPreferences(sharedPreferences)

    }

    @Provides
    @Singleton
    fun provideFilterOutUseCase() : FilterOutDigits{
        return FilterOutDigits()
    }

}