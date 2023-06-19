package com.example.chatapplication.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chatapplication.Constants
import com.example.chatapplication.DataUtils
import com.example.chatapplication.base.BaseViewModel
import com.example.chatapplication.database.getUserFromFireStore
import com.example.chatapplication.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    private val auth: FirebaseAuth = Firebase.auth
    lateinit var navigator: Navigator
    fun login() {
        if (validate()) {
            showLoading.value = true
            auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        checkUser(task.result.user!!.uid)
                        Log.e("success", "email is " + email.get())

                    }

                }
        }
    }

    private fun checkUser(uid: String) {

        getUserFromFireStore(uid, onSuccessListener = { docSnapshot ->
            showLoading.value = false
            val user = docSnapshot.toObject(AppUser::class.java)
            DataUtils.USER = user
            Log.e("login", "user $user")
            Log.e("login", "USER ${DataUtils.USER}")
            hasLogin()
            navigator.navigateToHomeActivity()

        }) { exception ->
            messageLiveData.value = exception.localizedMessage
        }
    }

    private fun hasLogin() {
        Constants.EDITOR!!.putBoolean("hasLoggedIn", true)
        Constants.EDITOR!!.commit()
    }

    fun navigateToRegisterActivity() {
        navigator.navigateToRegisterActivity()
    }

    private fun validate(): Boolean {
        var validate = true
        if (email.get().isNullOrBlank()) {
            emailError.set("Please enter the email")
            validate = false
        } else
            emailError.set(null)
        if (password.get().isNullOrBlank()) {
            passwordError.set("Please enter your password")
            validate = false
        } else
            passwordError.set(null)
        return validate
    }
}