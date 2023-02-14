package com.example.chatapplication.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

open abstract class BaseActivity<DB: ViewDataBinding,VM : BaseViewModel<*>> : AppCompatActivity() {
    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    var alertDialog: AlertDialog?=null
    lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()

        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel = initViewModeL()
    }
    fun subscribeToLiveData(){
        viewModel.messageLiveData.observe(this) { message ->
            showAlertDialog(message,"Ok")
        }
        viewModel.showLoading.observe(this) { loadingMessage ->
            if(loadingMessage == true){
                showProgressDialog()
            }
            else{
                hideProgressDialog()
            }
        }
    }

    abstract fun initViewModeL() : VM
    abstract fun getLayoutId() : Int

    fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
     fun showAlertDialog(
        message:String,posActionName:String?=null,
        posActionListener:DialogInterface.OnClickListener?=null,
        negActionName : String?=null,
        negActionListener:DialogInterface.OnClickListener?=null,
        cancellable: Boolean = true
    ){
        var builder = AlertDialog.Builder(this)
            .setMessage(message)
        if(posActionName != null){
            builder.setPositiveButton(posActionName, posActionListener)
        }
        if(negActionName != null){
            builder.setNegativeButton(negActionName,negActionListener)
        }
        builder.setCancelable(cancellable)
        alertDialog = builder.show()
    }
    fun showProgressDialog(){
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading....")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }
    fun hideProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss()
        }

    }

}