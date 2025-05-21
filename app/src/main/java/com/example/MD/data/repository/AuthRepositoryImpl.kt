package com.example.MD.data.repository

import android.util.Log
import androidx.core.content.edit
import com.example.MD.Domain.Auth
import com.example.MD.Domain.LoginCredentialsModel
import com.example.MD.Domain.UserRegisterModel
import com.example.MD.SingUpActivity
import com.example.MD.data.Retrofit
import com.example.MD.data.mapper.LoginCredentialsModelDTOMapper
import com.example.MD.data.mapper.UserRegisterModelDTOMapper
import kotlinx.serialization.json.Json.Default.parseToJsonElement
import kotlinx.serialization.json.jsonObject

class AuthRepositoryImpl(
    private val registerModelDTOMapper: UserRegisterModelDTOMapper,
    private val loginModelDTOMapper: LoginCredentialsModelDTOMapper
) : Auth {
    override suspend fun register(user: UserRegisterModel) {
        try{
           Retrofit.Auth.register(registerModelDTOMapper.map(user))
            val response = Retrofit.Auth.register(registerModelDTOMapper.map(user))
            var token = parseToJsonElement(response.string()).jsonObject["token"].toString()
            SingUpActivity.getApp().appSharedPref.edit {
                putString("token", token.substring(1, token.length - 1))
                apply()
            }
        }
        catch(ex:Exception){
            Log.e("","bad request")
        }
    }
    override suspend fun login(user: LoginCredentialsModel) {
        try{
            val response = Retrofit.Auth.login(loginModelDTOMapper.map(user))
            var token = parseToJsonElement(response.string()).jsonObject["token"].toString()
            SingUpActivity.getApp().appSharedPref.edit {
                putString("token", token.substring(1, token.length - 1))
                apply()
            }
        }
        catch(e:retrofit2.HttpException){
            Log.e("","bad request")
        }
    }

    override suspend fun logout() {
        try{
            val response = Retrofit.Auth.logout()
            SingUpActivity.getApp().appSharedPref.edit{
                putString("token","")
                putString("username","")
                putString("passwd","")
                apply()
            }
        }
        catch(e:retrofit2.HttpException){
            Log.e("","bad request")
        }
    }
}
