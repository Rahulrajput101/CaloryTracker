package com.plcoding.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {
    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
     fun setUp(){

         val preference = mockk<Preference>(relaxed = true)
           every { preference.loadUserInfo()} returns UserInfo(
               gender = Gender.Male,
               age = 20,
               weight = 80f,
               height =180 ,
               activityLevel = ActivityLevel.Medium,
               goalType = GoalType.KeepWeight,
               carbRatio = 0.4f,
               proteinRatio = 0.3f,
               fatRatio = 0.3f,
           )

        calculateMealNutrients = CalculateMealNutrients(preference)

    }
    @Test
    fun `Calories for breakfast properly calculated` (){

        val trackFood = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast","lunch","dinner","snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )

        }

        val result = calculateMealNutrients(trackFood)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.BreakFast}
            .sumOf { it.calories }

        val expectedBreakfastCalories = trackFood
            .filter { it.mealType == MealType.BreakFast}
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedBreakfastCalories)


    }

    @Test
    fun `Carbs for dinner properly calculated` (){

        val trackFood = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast","lunch","dinner","snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )

        }

        val result = calculateMealNutrients(trackFood)

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType == MealType.Dinner}
            .sumOf { it.carbs }

        val expectedDinnerCarbs = trackFood
            .filter { it.mealType == MealType.Dinner}
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(expectedDinnerCarbs)


    }
}