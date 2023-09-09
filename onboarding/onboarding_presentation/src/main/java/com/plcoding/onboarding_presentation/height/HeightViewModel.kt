package com.plcoding.onboarding_presentation.height

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.prefernces.Preference
import com.plcoding.core.domain.use_case.FilterOutDigits
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import com.plcoding.core.util.UiText
import com.plcoding.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preference: Preference,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var height by mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent  =_uiEvent.receiveAsFlow()

    fun onHeightEnter(height : String){
        if(height.length <= 3){
            Log.e("height1","changeed")
            this.height = filterOutDigits(height)
        }

    }

    fun onNextClick(){
        Log.e("height1","next clicked")
        viewModelScope.launch{
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                Log.e("height1","height empty ")
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preference.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }




}