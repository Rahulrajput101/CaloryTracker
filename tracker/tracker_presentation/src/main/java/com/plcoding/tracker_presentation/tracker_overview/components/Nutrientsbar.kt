package com.plcoding.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.plcoding.core_ui.CarbColor
import com.plcoding.core_ui.FatColor
import com.plcoding.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs : Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background  = MaterialTheme.colors.background
    val caloriesExceededColor =MaterialTheme.colors.error

    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    /**
     * The calculation takes the carbs value, multiplies it by 4 to convert it to calories
     * (since 1 gram of carbs = 4 calories), and then divides it by the calorieGoal.
     * This will give you a ratio of how many calories from carbs you've consumed compared to your calorie goal.
     * For example, if carbs is 50 grams and calorieGoal is 2000 calories,
     * the target value for the animation would be (50 * 4) / 2000 = 0.1.
     * This means you've consumed 10% of your daily calorie goal from carbohydrates
     */

    LaunchedEffect(key1 = carbs){
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(key1 = carbs){
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(key1 = carbs){
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / calorieGoal)
        )
    }

    Canvas(modifier = modifier ){
        if(calories <= calorieGoal){
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        }else{

            drawRoundRect(
                color = caloriesExceededColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }


    }





}