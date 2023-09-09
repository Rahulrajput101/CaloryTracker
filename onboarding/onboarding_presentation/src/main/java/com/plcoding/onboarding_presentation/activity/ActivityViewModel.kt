package com.plcoding.onboarding_presentation.activity

import android.app.Activity
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    val preference: Preference
) : ViewModel() {

    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent  =_uiEvent.receiveAsFlow()


    fun onActivityLevelSelected(activityLevel: ActivityLevel){
         selectedActivityLevel = activityLevel
    }

    fun onNextClick(){
        viewModelScope.launch {
            preference.activityLevel(selectedActivityLevel)
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
        }
    }


}