package com.example.chatapplication.ui.home

import com.example.chatapplication.base.BaseViewModel

class HomeViewModel : BaseViewModel<Navigator>() {
    lateinit var navigator : Navigator
    fun createRoom(){
        navigator.navigateToAddRoom()
    }
}