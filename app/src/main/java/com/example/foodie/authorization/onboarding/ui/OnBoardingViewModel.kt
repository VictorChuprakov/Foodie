package com.example.foodie.authorization.onboarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.dataPreference.DataPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataPreference: DataPreference,
) : ViewModel() {

    fun saveStateOnBoarding(completed:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataPreference.saveStateOnBoarding(completed)
        }
    }
}