package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MD.Domain.RegisterUseCase

class SignUpFactory (
    private val signUpUseCase: RegisterUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingUpViewModel::class.java)) {
            return SingUpViewModel(signUpUser = signUpUseCase) as T
        }
        throw IllegalArgumentException("UnLuck")
    }
}