package com.plcoding.core.data.preferences

import android.content.SharedPreferences
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.prefernces.Preference

class DefaultPreferences(
    private val sharedPref: SharedPreferences
) : Preference {
    override fun saveGender(gender: Gender) {
         sharedPref.edit()
             .putString(Preference.KEY_GENDER,gender.name)
             .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(Preference.KEY_AGE,age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(Preference.KEY_WEIGHT,weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(Preference.KEY_HEIGHT,height)
            .apply()
    }

    override fun activityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(Preference.KEY_ACTIVITY_LEVEL,level.name)
            .apply()
    }

    override fun goalType(type: GoalType) {
        sharedPref.edit()
            .putString(Preference.KEY_GOAL_TYPE,type.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preference.KEY_CARB_RATIO,ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preference.KEY_PROTEIN_RATIO,ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preference.KEY_FAT_RATIO,ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(Preference.KEY_AGE,-1)
        val height = sharedPref.getInt(Preference.KEY_HEIGHT,-1)
        val weight = sharedPref.getFloat(Preference.KEY_WEIGHT,-1f)
        val genderString = sharedPref.getString(Preference.KEY_GENDER,null)
        val activityLevelString = sharedPref.getString(Preference.KEY_ACTIVITY_LEVEL,null)
        val goalTypeString = sharedPref.getString(Preference.KEY_GOAL_TYPE,null)
        val carbRatio = sharedPref.getFloat(Preference.KEY_CARB_RATIO,-1f)
        val proteinRatio = sharedPref.getFloat(Preference.KEY_PROTEIN_RATIO,-1f)
        val fatRatio = sharedPref.getFloat(Preference.KEY_FAT_RATIO,-1f)

       return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight" ),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }
}