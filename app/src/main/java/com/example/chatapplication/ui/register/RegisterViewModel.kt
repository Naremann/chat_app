package com.example.chatapplication.ui.register
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapplication.base.BaseViewModel
import com.example.chatapplication.database.addUserToFireStore
import com.example.chatapplication.model.AppUser
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
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
    lateinit var navigator : Navigator
    //val messageLiveData = MutableLiveData<String>()
    //val showLoading = MutableLiveData<Boolean>()
    private var auth: FirebaseAuth = Firebase.auth
    fun createAccount(){
        if(validate()){
            addAccountToFirebase()
        }
    }

    private fun addAccountToFirebase() {
        showLoading.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    createFireStoreUser(task.result.user!!.uid)


                    Log.d(TAG, "createUserWithEmail:success")

                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("createUserWithEmail", "failed"+task.exception?.localizedMessage)
                    //Toast.makeText(this, "Authentication failed.",
                    //  Toast.LENGTH_SHORT).show()
                    //updateUI(null)

                }
            }
    }

    private fun createFireStoreUser(id:String) {
        var user = AppUser(id, firstName.get(),lastName.get(),email.get())
        addUserToFireStore(user,{
            //messageLiveData.value = "account is created successfully"
            navigator.navigateToLoginActivity()


        },{ exception->
            messageLiveData.value = exception.localizedMessage

        })

    }

    fun validate(): Boolean {
        var validate = true
        if(firstName.get().isNullOrBlank()){
            firstNameError.set("Please enter first name")
            validate = false
        }
        else
            firstNameError.set(null)
        if(lastName.get().isNullOrBlank()){
            lastNameError.set("Please enter last name")
            validate = false
        }
        else
            lastNameError.set(null)
        if(email.get().isNullOrBlank()){
            emailError.set("Please enter the email")
            validate = false
        }
        else
            emailError.set(null)
        if(password.get().isNullOrBlank()) {
            passwordError.set("Please enter your password")
            validate = false
        }
        else
            passwordError.set(null)
        return validate

    }
}