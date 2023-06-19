package com.example.chatapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.databinding.ItemRoomBinding
import com.example.chatapplication.model.Room

class RoomItemAdapter(var roomItems: List<Room?>?) :
    RecyclerView.Adapter<RoomItemAdapter.ViewHolder>() {
    var onItemClickListener: OnItemClickListener? = null

    class ViewHolder(val viewDataBinding: ItemRoomBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        var title: TextView = viewDataBinding.title
        var image: ImageView = viewDataBinding.image
        fun binding(room: Room) {
            viewDataBinding.roomItem = room
            viewDataBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = DataBindingUtil.inflate<ItemRoomBinding>(
            LayoutInflater.from(parent.context), R.layout.item_room, parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room: Room? = roomItems!![position]
        holder.binding(room!!)
        holder.itemView.setOnClickListener {
            onItemClickListener!!.onItemClick(room)
        }
    }

    override fun getItemCount(): Int {
        return roomItems?.size ?: 0
    }

    fun changeData(roomList: List<Room>) {
        roomItems = roomList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(room: Room)
    }
}