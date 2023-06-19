package com.example.chatapplication.model

import com.google.firebase.Timestamp

data class Message (
    var messageId : String?=null,
    val senderName : String?=null,
    val senderId:String?=null,
    var messageContent : String?=null,
    val time : String?=null,
    val roomId:String?=null
        ){
    companion object{
        const val COLLECTION_NAME = "message"
    }
}