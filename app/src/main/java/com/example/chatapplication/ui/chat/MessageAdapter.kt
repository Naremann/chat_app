package com.example.chatapplication.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.DataUtils
import com.example.chatapplication.R
import com.example.chatapplication.databinding.RecievedMessageItemBinding
import com.example.chatapplication.databinding.SentMessageItemBinding
import com.example.chatapplication.model.Message

class MessageAdapter(var messages: MutableList<Message> = mutableListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val sent = 1
    private val received = 0

    class SenderViewHolder(val viewDataBinding: SentMessageItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root){
        fun bind(message:Message){
            viewDataBinding.message=message
            viewDataBinding.invalidateAll()
        }
    }
    class ReceiverViewHolder(val viewDataBinding: RecievedMessageItemBinding):RecyclerView.ViewHolder(viewDataBinding.root){
        fun bind(message:Message){
            viewDataBinding.receiverMessage=message
            viewDataBinding.invalidateAll()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        if(message.senderId==DataUtils.USER?.id){
            return sent
        }
        return received

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==sent){
            val itemBinding = DataBindingUtil.inflate<SentMessageItemBinding>(LayoutInflater.from(parent.context)
                , R.layout.sent_message_item,parent,false
            )
            return SenderViewHolder(itemBinding)
        }
        val itemBinding = DataBindingUtil.inflate<RecievedMessageItemBinding>(LayoutInflater.from(parent.context)
            , R.layout.recieved_message_item,parent,false
        )
        return ReceiverViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReceiverViewHolder){
            holder.bind(messages[position])
        }
        else if(holder is SenderViewHolder){
            holder.bind(messages[position])
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }
    fun changeData(message: MutableList<Message>){
        messages.addAll(message)
        notifyItemRangeInserted(messages.size+1,message.size)
    }
}