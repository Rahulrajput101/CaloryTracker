package com.plcoding.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    val preference: Preference
): ViewModel(){
    var selectedGoalType by mutableStateOf<GoalType>(GoalType.LoseWeight)
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent  =_uiEvent.receiveAsFlow()


    fun onGoalTypeSelected(goalType: GoalType){
        selectedGoalType = goalType
    }

    fun onNextClick(){
        viewModelScope.launch {
            preference.goalType(selectedGoalType)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }


}