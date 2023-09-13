package com.plcoding.tracker_domain.di

import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.tracker_domain.repository.TrackerRepository
import com.plcoding.tracker_domain.use_case.CalculateMealNutrients
import com.plcoding.tracker_domain.use_case.DeleteTrackedFood
import com.plcoding.tracker_domain.use_case.GetFoodsForDate
import com.plcoding.tracker_domain.use_case.SearchFood
import com.plcoding.tracker_domain.use_case.TrackFood
import com.plcoding.tracker_domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preference
    ): TrackerUseCases {

        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }

}