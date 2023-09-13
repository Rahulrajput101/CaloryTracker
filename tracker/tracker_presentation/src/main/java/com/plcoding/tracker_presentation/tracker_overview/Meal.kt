package com.plcoding.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.plcoding.core.util.UiText
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_presentation.R

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carb: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calorie: Int = 0,
    val isExpanded : Boolean = false
)

val defaultMeal = listOf<Meal>(
    Meal(
        name = UiText.StringResource(R.string.breakfast),
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.BreakFast
    ),

    Meal(
        name = UiText.StringResource(R.string.lunch),
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch
    ),

    Meal(
        name = UiText.StringResource(R.string.dinner),
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = UiText.StringResource(R.string.snacks),
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.Snack
    )
)
