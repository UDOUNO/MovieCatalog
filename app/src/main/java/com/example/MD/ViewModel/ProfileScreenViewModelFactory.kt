package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MD.Domain.AddFavGenreUseCase
import com.example.MD.Domain.AddFavoritesUseCase
import com.example.MD.Domain.EditProfileUseCase
import com.example.MD.Domain.GetFavGenresUseCase
import com.example.MD.Domain.GetProfileUseCase
import com.example.MD.Domain.LogoutUseCase
import com.example.MD.Domain.MoviesPageUseCase

class ProfileScreenViewModelFactory(
    private val getUserProfile: GetProfileUseCase,
    private val editProfileUseCase: EditProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileScreenViewModel::class.java)) {
            return ProfileScreenViewModel(
                getUserProfile, editProfileUseCase, logoutUseCase
            ) as T
        }
        throw IllegalArgumentException("UnLuck")
    }
}