package com.plcoding.tracker_domain.model

sealed class MealType(val name: String) {
   object BreakFast: MealType("breakfast")
   object Lunch: MealType("lunch")
   object Dinner: MealType("dinner")
   object Snack: MealType("snack")


    companion object{

        fun fromString( name: String) : MealType{
            return when(name){
                "breakfast" -> MealType.BreakFast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> BreakFast
            }
        }
    }

}
