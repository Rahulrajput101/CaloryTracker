package com.plcoding.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
   val preference: Preference
) : ViewModel(){

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
    private set

    private val _uiEvent = Channel<UiEvent>()
     var uiEvent  =_uiEvent.receiveAsFlow()


    fun onGenderClick(gender: Gender){
        selectedGender = gender
    }

    fun onNextClick(){
        viewModelScope.launch {
            preference.saveGender(selectedGender)
            _uiEvent.send(UiEvent.Navigate(Route.AGE))
        }
    }




}