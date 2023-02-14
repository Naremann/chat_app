package com.example.chatapplication.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.R
import com.example.chatapplication.base.BaseActivity
import com.example.chatapplication.databinding.ActivityLoginBinding
import com.example.chatapplication.ui.home.HomeActivity
import com.example.chatapplication.ui.register.RegisterActivity

class LoginActivity :BaseActivity<ActivityLoginBinding,LoginViewModel>() ,Navigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        subscribeToLiveData()
        viewModel.navigator = this

    }

    override fun initViewModeL(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun navigateToHomeActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    override fun navigateToRegisterActivity() {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}