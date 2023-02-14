package com.example.chatapplication

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        val settings = getSharedPreferences(Constants.SHARED_PREF, 0)
        Constants.SETTING = settings
        val editor = settings.edit()
        Constants.EDITOR = editor
        val result = settings.getBoolean(Constants.HAS_LOGIN, false)
        Constants.isLogin = result
    }
}