package com.example.chatapplication.ui.chat

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chatapplication.DataUtils
import com.example.chatapplication.base.BaseViewModel
import com.example.chatapplication.database.addMessage
import com.example.chatapplication.model.Message
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ChatViewModel : BaseViewModel<Navigator>() {
    private var datum: DateFormat = SimpleDateFormat("h:mm")
    private var datu: DateFormat = DateFormat.getTimeInstance()
    var messageField=ObservableField<String>()
    var toastLiveData=MutableLiveData<String>()
    var roomId : String?=null

    fun sendMessage(){
        val calender = Calendar.getInstance()
        calender.clear(Calendar.YEAR)
        calender.clear(Calendar.MONTH)
        calender.clear(Calendar.DAY_OF_MONTH)
        calender.clear(Calendar.SECOND)
        calender.clear(Calendar.MILLISECOND)
        val message = Message(
            messageContent = messageField.get(),
            roomId = roomId,
            senderId = DataUtils.USER?.id,
            senderName = DataUtils.USER?.firstName,
            time = datu.format(calender.time))
          //  time = datum.format(Calendar.getInstance().time))
        addMessage(message, {
            messageField.set("")
            Log.e("add message","message is added")

        }
        ) {exception->
            toastLiveData.value=exception.localizedMessage
        }
    }
}