package com.example.chatapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.Constants
import com.example.chatapplication.R
import com.example.chatapplication.base.BaseActivity
import com.example.chatapplication.databinding.ActivitySplashBinding
import com.example.chatapplication.ui.home.HomeActivity
import com.example.chatapplication.ui.login.LoginActivity


class SplashScreenActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({ startActivity() }, 3000)
    }


    override fun startActivity() {
        if (Constants.isLogin == true) {
            startActivity(HomeActivity::class.java)
        } else {
            startActivity(LoginActivity::class.java)
        }
    }

    private fun startActivity(activity: Class<*>) {
        intent = Intent(this, activity)
        startActivity(intent)

    }

    override fun initViewModeL(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}