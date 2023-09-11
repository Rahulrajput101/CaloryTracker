package com.plcoding.tracker_data.mapper

import com.plcoding.tracker_data.local.TrackedFoodEntity
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood() : TrackedFood{

    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        calories = calories,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        id = id
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        calories = calories,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        year = date.year,
        month = date.monthValue,
        dayOfMonth = date.dayOfMonth,
        id = id
    )


}