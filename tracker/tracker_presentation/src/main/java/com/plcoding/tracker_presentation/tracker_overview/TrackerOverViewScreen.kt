package com.plcoding.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.tracker_presentation.tracker_overview.components.NutrientsHeader
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel  = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ){

        item {
            NutrientsHeader(state = state)
        }

    }
    
}