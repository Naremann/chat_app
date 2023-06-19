package com.example.chatapplication.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chatapplication.base.BaseViewModel
import com.example.chatapplication.database.addRoom
import com.example.chatapplication.model.CategoryItem
import com.example.chatapplication.model.Room

class AddRoomViewModel : BaseViewModel<Navigator>() {

    val roomName = ObservableField<String>()
    val roomDesc = ObservableField<String>()
    val roomNameError = ObservableField<String>()
    val roomDescError = ObservableField<String>()
    var categoryList = CategoryItem.getCategoryList()
    var selectedCategory = categoryList[0]
    var navigator : Navigator?=null
    val isRoomAdded = MutableLiveData<Boolean>()

    fun createRoom(){
        val room = Room(name = roomName.get(), desc = roomDesc.get(), categoryId = selectedCategory.id)
        if(validate()){

            showLoading.value=true
            addRoom(room, { exception->
               showLoading.value=false
                messageLiveData.value = exception.localizedMessage

            }, {
                showLoading.value = false
                isRoomAdded.value=true
            })
        }
    }

    private fun validate() : Boolean{
        var isValid = true
        if(roomName.get().isNullOrBlank()){
            isValid = false
            roomNameError.set("Please enter room name")
        }
        else{
            roomNameError.set(null)
        }
        if(roomDesc.get().isNullOrBlank()){
            isValid = false
            roomDescError.set("Please enter room description")
        }
        else{
            roomDescError.set(null)
        }
        return isValid
    }
}