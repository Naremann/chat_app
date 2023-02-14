package com.example.chatapplication.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.base.BaseActivity
import com.example.chatapplication.database.getRoom
import com.example.chatapplication.databinding.ActivityHomeBinding
import com.example.chatapplication.model.Room
import com.example.chatapplication.ui.addRoom.AddRoomActivity
import com.example.chatapplication.ui.chat.ChatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(),Navigator {
    lateinit var roomAdapter : RoomItemAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        initRecyclerView()

    }

    private fun initRecyclerView() {
        roomAdapter = RoomItemAdapter(null)
        recyclerView = viewDataBinding.recyclerView
        recyclerView.adapter=roomAdapter
        roomAdapter.onItemClickListener = object :RoomItemAdapter.OnItemClickListener{
            override fun onItemClick(room: Room) {
                startChatActivity(room)
            }
        }
    }

    private fun startChatActivity(room: Room) {
        val intent=Intent(this,ChatActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        getRoom({ querySnapshot->
           val  roomList = querySnapshot.toObjects(Room::class.java)
            roomAdapter.changeData(roomList)

        }, {

        })
    }

    override fun initViewModeL(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun navigateToAddRoom() {
        val intent = Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }
}