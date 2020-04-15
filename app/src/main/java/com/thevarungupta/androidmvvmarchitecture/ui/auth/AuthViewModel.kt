package com.thevarungupta.androidmvvmarchitecture.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.thevarungupta.androidmvvmarchitecture.data.repository.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButton(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("invalid email and password")
            // error message
            return
        }
        // success
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }

}