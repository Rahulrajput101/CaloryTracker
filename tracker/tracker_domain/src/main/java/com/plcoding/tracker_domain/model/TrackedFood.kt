package com.plcoding.tracker_domain.model

import java.time.LocalDate

data class TrackedFood(
    val name: String,
    val carbs: String,
    val protein: String,
    val fat: String,
    val imageUrl: String,
    val mealType: MealType,
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
     val id: Int? = null
) {



}
