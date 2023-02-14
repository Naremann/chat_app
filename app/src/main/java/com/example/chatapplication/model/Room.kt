package com.example.chatapplication.model

data class Room(
    var id:String?=null,
    val name:String?=null,
    val desc:String?=null,
    val categoryId:String?=null
){
    fun getImageId() : Int{
        return CategoryItem.getCategoryById(categoryId = categoryId ?: CategoryItem.SPORT ).imageId!!
    }
    companion object{
        const val COLLECTION_NAME = "rooms"
    }
}