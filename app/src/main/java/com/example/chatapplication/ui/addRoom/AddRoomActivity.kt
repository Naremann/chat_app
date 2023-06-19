package com.example.chatapplication.ui.addRoom

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.R
import com.example.chatapplication.base.BaseActivity
import com.example.chatapplication.databinding.ActivityAddRoomBinding
import com.example.chatapplication.model.CategoryItem


class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        initCategorySpinner()
        subscribeToLiveData()
        observeToLiveData()
    }

    private fun initCategorySpinner() {
        val spinnerAdapter = CategoriesSpinnerAdapter(CategoryItem.getCategoryList())
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
    }


    private fun observeToLiveData() {
        viewModel.isRoomAdded.observe(this) { isRoomAdded ->
            if (isRoomAdded == true) {
                showAlertDialog(
                    "Room is added successfully",
                    "Ok",
                     { _, _ ->
                        hideProgressDialog()
                        finish()
                    },
                    cancellable = false
                )
            }

        }
    }

    override fun initViewModeL(): AddRoomViewModel {
        return ViewModelProvider(this)[AddRoomViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

}