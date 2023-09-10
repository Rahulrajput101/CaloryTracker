package com.plcoding.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.domain.use_case.FilterOutDigits
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import com.plcoding.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preference: Preference,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
): ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent  =_uiEvent.receiveAsFlow()


    fun onEvent(event: NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(
                    carbRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(
                    fatRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(
                    proteinRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnNextClick -> {
              val result =  validateNutrients(
                    carbsRatio = state.carbRatio,
                    proteinRatio = state.proteinRatio,
                    fatRatio = state.fatRatio
                )

                when(result){
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                        }
                    }
                    is ValidateNutrients.Result.Success -> {
                        preference.saveCarbRatio(result.carbsRatio)
                        preference.saveProteinRatio(result.proteinRation)
                        preference.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                }
            }
        }




    }



}