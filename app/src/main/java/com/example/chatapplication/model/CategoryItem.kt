package com.example.chatapplication.model

import com.example.chatapplication.R

data class CategoryItem(
    val id : String?=null,
    val name : String?=null,
    val imageId:Int?=null
){

   companion object{
       const val MUSIC = "music"
       const val SPORT = "sport"
       const val MOVIE = "music"
       fun getCategoryById(categoryId:String):CategoryItem{
           return when(categoryId){
               MUSIC-> CategoryItem(MUSIC,"Music", R.drawable.music)
               SPORT-> CategoryItem(SPORT,"Sport", R.drawable.sports)
               else -> CategoryItem(MOVIE,"Movie", R.drawable.movies)
           }

       }
       fun getCategoryList():List<CategoryItem>{
           return listOf(
               getCategoryById(MUSIC),
               getCategoryById(SPORT),
               getCategoryById(MOVIE)
           )
       }

   }
}