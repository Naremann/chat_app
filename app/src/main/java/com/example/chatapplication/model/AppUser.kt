package com.example.chatapplication.model

data class AppUser (
    var id : String? = null,
    var firstName : String? = null,
    var lastName : String? = null,
    var email : String? = null,
    )
{
    companion object{
        val COLLECTION_NAME ="user"
    }
}