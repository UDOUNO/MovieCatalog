package com.example.MD.addons

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: App? = null
            private set
    }
}

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val application = App.instance
    }
}