package com.example.chatapplication.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapplication.Constants
import com.example.chatapplication.R
import com.example.chatapplication.base.BaseActivity
import com.example.chatapplication.database.getMessageReference
import com.example.chatapplication.databinding.ActivityChatBinding
import com.example.chatapplication.model.Message
import com.example.chatapplication.model.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query


class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>(){
    var room: Room?=null
    private var messageAdapter: MessageAdapter = MessageAdapter()
    private var roomId : String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        roomId = intent.getParcelableExtra<Room>(Constants.EXTRA_ROOM)!!.id
        viewModel.roomId=roomId
        subscribeToLiveData()
        observeToLiveData()
        initRecyclerview()
        listenForMessageUpdates()
    }

    private fun listenForMessageUpdates() {
        val messages= mutableListOf<Message>()
        roomId?.let {
            getMessageReference(it)
                .orderBy("time", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshots, ex ->
                if (ex != null) {
                    Log.e("TAG", "listen:error", ex)
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val message = dc.document.toObject(Message::class.java)
                            Log.e("doc", "list +$message")
                            messages.add(message)

                        }
                        else -> {
                        }
                    }
                    Log.e("snapshot", "list + $messages")
                }
                messageAdapter.changeData(messages)
                messageAdapter.notifyDataSetChanged()
                viewDataBinding.recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
                Log.e("uo", "list is" + messageAdapter.messages)
            }
        }
    }

    private fun initRecyclerview() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd=true
        viewDataBinding.recyclerView.layoutManager = linearLayoutManager
        viewDataBinding.recyclerView.adapter = messageAdapter

    }

    private fun observeToLiveData() {
        viewModel.toastLiveData.observe(this) { message ->
            showToastMessage(message)
        }
    }

    override fun initViewModeL(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

}