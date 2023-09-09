package com.plcoding.onboarding_presentation.height

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.UnitTextFiled
import kotlinx.coroutines.flow.collect

@Composable
fun HeightScreen(
    scaffoldState: ScaffoldState,
    onNavigate : (UiEvent.Navigate) -> Unit,
    viewModel: HeightViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect {event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    Log.d("height1","collected")
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else  -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextFiled(
                value = viewModel.height,
                onValueChange = viewModel::onHeightEnter,
                unit = stringResource(id = R.string.cm)
            )

        }

        ActionButton(
            text = stringResource(id = R.string.next),
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = viewModel::onNextClick,
        )
    }

}