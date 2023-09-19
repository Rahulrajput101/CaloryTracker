package com.plcoding.tracker_presentation.tracker_overview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import com.plcoding.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
      preference: Preference,
      private val trackerUseCases: TrackerUseCases,
) : ViewModel() {

       var state by mutableStateOf(TrackerOverViewState())
             private set

      private var getFoodsForDateJob : Job? = null

      init {
            refreshFood()
            preference.saveShouldShowOnBoarding(false)
      }

      private val _uiEvent = Channel<UiEvent>()
       var uiEvent = _uiEvent.receiveAsFlow()

      fun onEvent(event: TrackerOverViewEvent){
            when(event){
                  is TrackerOverViewEvent.OnAddFoodClick -> {
                        viewModelScope.launch {
                              _uiEvent.send(
                                    UiEvent.Navigate(
                                          route = Route.SEARCH
                                                  + "/${event.meal.mealType.name}"
                                                  + "/${state.localDate.dayOfMonth}"
                                                  + "/${state.localDate.monthValue}"
                                                  + "/${state.localDate.year}"
                                    )
                              )
                        }
                  }
                  is TrackerOverViewEvent.OnDeleteTrackedFoodClick -> {
                        viewModelScope.launch {

                              trackerUseCases.deleteTrackedFood(event.trackedFood)
                              refreshFood()
                        }
                  }
                  TrackerOverViewEvent.OnNextDayClick -> {
                        state = state.copy(
                              localDate = state.localDate.plusDays(1)
                        )
                        refreshFood()
                  }
                  TrackerOverViewEvent.OnPreviousDayClick -> {
                        state = state.copy(
                              localDate = state.localDate.minusDays(1)
                        )
                        refreshFood()
                  }
                  is TrackerOverViewEvent.OnToggleMealClick -> {
                        state = state.copy(
                              meal = state.meal.map {
                                     if(it.name == event.meal.name){
                                          it.copy(isExpanded = !it.isExpanded)
                                    }else{
                                          it
                                    }
                              }
                        )

                  }
            }
      }

      private fun refreshFood(){
            getFoodsForDateJob?.cancel()
           getFoodsForDateJob = trackerUseCases.getFoodsForDate(state.localDate)
                  .onEach { foods ->
                  val nutrientResult = trackerUseCases.calculateMealNutrients(foods)
                  state = state.copy(
                        totalCarbs = nutrientResult.totalCarbs,
                        totalProtein = nutrientResult.totalProtein,
                        totalFat = nutrientResult.totalFat,
                        totalCalories = nutrientResult.totalCalories,
                        carbsGoal = nutrientResult.carbsGoal,
                        proteinGoal = nutrientResult.proteinGoal,
                        fatGoal = nutrientResult.fatGoal,
                        caloriesGoal = nutrientResult.caloriesGoal,
                        trackedFood = foods,
                        meal = state.meal.map {
                               val nutrientsForMeal =
                                     nutrientResult.mealNutrients[it.mealType]
                                           ?: return@map it.copy(
                                                 carb = 0,
                                                 protein = 0,
                                                 fat = 0,
                                                 calorie = 0,
                                           )
                              it.copy(
                                    carb =nutrientsForMeal.carbs,
                                    protein = nutrientsForMeal.protein,
                                    fat = nutrientsForMeal.protein,
                                    calorie = nutrientsForMeal.calories
                              )

                        },

                  )

                  }.launchIn(viewModelScope)

      }


}