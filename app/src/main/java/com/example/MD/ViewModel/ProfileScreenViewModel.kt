package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MD.Domain.EditProfileUseCase
import com.example.MD.Domain.GetProfileUseCase
import com.example.MD.Domain.LogoutUseCase
import com.example.MD.Domain.ProfileModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val getUserProfile: GetProfileUseCase,
    private val editProfileUseCase: EditProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _profile = MutableStateFlow<ProfileModel?>(null)
    val profile: StateFlow<ProfileModel?> = _profile
    private val _updateConfirm = MutableStateFlow<Boolean>(false)
    val updateConfirm: StateFlow<Boolean> = _updateConfirm
    private val _logoutTrue = MutableStateFlow<Boolean>(false)
    val logoutTrue: StateFlow<Boolean> = _logoutTrue
    fun getProfile() {
        viewModelScope.launch { _profile.value = getUserProfile() }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _logoutTrue.value = true
        }
    }

    fun uploadImage(profile: ProfileModel) {
        viewModelScope.launch {
            editProfileUseCase(profile)
            _updateConfirm.value = true
        }
    }
}