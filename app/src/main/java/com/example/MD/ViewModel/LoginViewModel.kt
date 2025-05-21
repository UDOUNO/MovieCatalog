package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MD.Domain.LoginCredentialsModel
import com.example.MD.Domain.LoginUseCase
import com.example.MD.SingUpActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUser: LoginUseCase
) : ViewModel() {
    private val _loginExist = MutableStateFlow(false)
    val loginExist: StateFlow<Boolean> = _loginExist
    fun login(user: LoginCredentialsModel) {
        viewModelScope.launch { loginUser(user) }
        val token = SingUpActivity.getApp().appSharedPref.getString("token", "")
        if (token != "") {
            _loginExist.update { count -> !count }
        }
    }
}