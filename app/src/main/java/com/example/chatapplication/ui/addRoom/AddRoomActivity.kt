package com.example.chatapplication.ui.addRoom

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.R
import com.example.chatapplication.base.BaseActivity
import com.example.chatapplication.databinding.ActivityAddRoomBinding
import com.example.chatapplication.model.CategoryItem
import com.example.chatapplication.ui.home.HomeActivity

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>(){
    lateinit var spinnerAdapter: CategoriesSpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        spinnerAdapter = CategoriesSpinnerAdapter(CategoryItem.getCategoryList())
        viewDataBinding.categorySpinner.adapter = spinnerAdapter
        viewDataBinding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedCategory=viewModel.categoryList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        subscribeToLiveData()
        viewModel.isRoomAdded.observe(this, Observer { isRoomAddred->
            if (isRoomAddred==true){
                showAlertDialog("Room is added successfully","Ok",DialogInterface.OnClickListener { dialog, which ->
                    hideProgressDialog()
                    finish()
                }, cancellable = false)
            }

        })
    }

    override fun initViewModeL(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

}