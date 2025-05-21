package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MD.Domain.RegisterUseCase
import com.example.MD.Domain.UserRegisterModel
import com.example.MD.SingUpActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingUpViewModel (private val signUpUser: RegisterUseCase
) : ViewModel() {
    private val _loginExist = MutableStateFlow(false)
    val loginExist: StateFlow<Boolean> = _loginExist
    fun singUp(user: UserRegisterModel) {
        viewModelScope.launch { signUpUser(user) }
        val token = SingUpActivity.getApp().appSharedPref.getString("token","")
        if(token != ""){
            _loginExist.update { count -> !count }
        }
    }
}