package com.plcoding.tracker_domain.use_case

import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import kotlin.math.roundToInt

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackedFood
    ) {
        repository.deleteTrackedFood(food)
    }
}