package com.example.chatapplication.ui.addRoom

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.model.CategoryItem

class CategoriesSpinnerAdapter(private val categoryItems : List<CategoryItem>) : BaseAdapter(){
    override fun getCount(): Int {
        return categoryItems.size
    }

    override fun getItem(position: Int): Any {
        return categoryItems[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var myView = view
        var viewHolder : ViewHolder
        if(myView==null){
            myView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_category_spinner,parent,false)
            viewHolder = ViewHolder(myView)
            myView.tag = viewHolder
        }
        else{
            viewHolder = view!!.tag as ViewHolder
        }
        val title = categoryItems[position].name
        val image = categoryItems[position].imageId
        viewHolder.image.setImageResource(image!!)
        viewHolder.title.text = title

        return myView!!
    }

    class ViewHolder(val view: View){
        val title : TextView = view.findViewById(R.id.title)
        val image : ImageView = view.findViewById(R.id.item_img)
    }
}