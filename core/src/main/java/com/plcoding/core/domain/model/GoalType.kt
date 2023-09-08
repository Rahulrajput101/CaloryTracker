package com.plcoding.core.domain.model

sealed class GoalType(val name : String){
    object LoseWeight : GoalType("lose_weight")
    object KeepWeight : GoalType("keep_weight")
    object GainWeight : GoalType("gain_weight")


    companion object{
        fun fromString(name: String) : GoalType{
           return when(name){
               "lose_weight" -> GoalType.LoseWeight
               "keep_weight" -> GoalType.KeepWeight
               "gain_weight" -> GoalType.GainWeight
               else -> GoalType.KeepWeight
           }
        }

    }
}
