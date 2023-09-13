package com.plcoding.tracker_presentation.tracker_overview

import com.plcoding.tracker_domain.model.TrackedFood
import java.time.LocalDate

data class TrackerOverViewState (
    val totalCarbs: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val totalCalories: Int = 0,
    val carbsGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val caloriesGoal: Int = 0,
    val localDate: LocalDate = LocalDate.now(),
    val trackedFood: List<TrackedFood> = emptyList(),
    val meal: List<Meal> = defaultMeal
)