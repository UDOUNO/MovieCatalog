package com.example.MD

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.MD.View.WelcomeFragment
import com.example.MD.databinding.MainScreenBinding

class SingUpActivity : AppCompatActivity() {
    val APP_SHARED_PREF_NAME = "app_shared_pref"
    lateinit var appSharedPref: SharedPreferences
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        appSharedPref = getSharedPreferences(APP_SHARED_PREF_NAME, Context.MODE_PRIVATE)
        app = this
        val binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun restartApp(){
        val intent = Intent(this, SingUpActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        this.startActivity(intent)
    }

    companion object {
        private lateinit var app: SingUpActivity

        fun getApp(): SingUpActivity {
            return app
        }

        fun nullSharedPreffs(){
            app.appSharedPref.edit().clear().apply()
        }
    }
}
