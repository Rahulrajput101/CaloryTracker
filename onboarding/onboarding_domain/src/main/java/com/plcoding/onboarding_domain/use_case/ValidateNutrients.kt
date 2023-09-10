package com.plcoding.onboarding_domain.use_case

import com.plcoding.core.util.UiText
import com.plcoding.onboarding_domain.R

class ValidateNutrients {

    operator fun invoke(
          carbsRatio : String,
          proteinRatio : String,
          fatRatio : String,
    ) : Result{
        val carbsRatio = carbsRatio.toIntOrNull()
        val proteinRatio = proteinRatio.toIntOrNull()
        val fatRatio = fatRatio.toIntOrNull()

        if(carbsRatio == null || proteinRatio == null || fatRatio == null){
            return Result.Error(
               message = UiText.StringResource(R.string.error_invalid_values)
            )
        }

        if(carbsRatio + proteinRatio + fatRatio != 100){
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return Result.Success(
            carbsRatio / 100f,
            proteinRatio / 100f,
            fatRatio / 100f)
    }


    sealed class Result{

        data class Success(
            val carbsRatio: Float,
            val proteinRation: Float,
            val fatRatio: Float
        ) : Result()

        data class Error(val message : UiText) : Result()
    }

}