package com.example.chatapplication.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chatapplication.base.BaseViewModel
import com.example.chatapplication.database.addUserToFireStore
import com.example.chatapplication.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : BaseViewModel<Navigator>() {
    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val firstNameError = ObservableField<String>()
    val lastNameError = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    lateinit var navigator: Navigator
    private var auth: FirebaseAuth = Firebase.auth
    fun createAccount() {
        if (validate()) {
            addAccountToFirebase()
        }
    }

    private fun addAccountToFirebase() {
        showLoading.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (task.isSuccessful) {
                    createFireStoreUser(task.result.user!!.uid)
                } else {
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("createUserWithEmail", "failed" + task.exception?.localizedMessage)
                }
            }
    }

    private fun createFireStoreUser(id: String) {
        val user = AppUser(id, firstName.get(), lastName.get(), email.get())
        addUserToFireStore(user, {
            navigator.navigateToLoginActivity()


        }, { exception ->
            messageLiveData.value = exception.localizedMessage

        })

    }

    fun validate(): Boolean {
        var validate = true
        if (firstName.get().isNullOrBlank()) {
            firstNameError.set("Please enter first name")
            validate = false
        } else
            firstNameError.set(null)
        if (lastName.get().isNullOrBlank()) {
            lastNameError.set("Please enter last name")
            validate = false
        } else
            lastNameError.set(null)
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