package com.example.chatapplication

import android.content.SharedPreferences
import com.example.chatapplication.model.AppUser
import com.google.firebase.firestore.auth.User

class Constants {
    companion object{
        var EDITOR : SharedPreferences.Editor?=null
        var SETTING : SharedPreferences?=null
        var HAS_LOGIN = "hasLoggedIn"
        var isLogin : Boolean?=null
        const val SHARED_PREF = "MyPrefsFile"
        const val EXTRA_ROOM = "room"

    }
}