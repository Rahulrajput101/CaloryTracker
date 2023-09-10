package com.plcoding.onboarding_domain.di

import com.plcoding.core.domain.use_case.FilterOutDigits
import com.plcoding.onboarding_domain.use_case.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule{

    @Provides
    @ViewModelScoped
    fun provideValidationNutrientsUseCase() : ValidateNutrients {
        return ValidateNutrients()
    }
}