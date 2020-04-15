package com.thevarungupta.androidmvvmarchitecture.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.thevarungupta.androidmvvmarchitecture.R
import com.thevarungupta.androidmvvmarchitecture.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = authViewModel

        authViewModel.authListener = this

    }

    override fun onStarted() {
        Log.d("aaa", "onStarted")
        progress_bar.visibility = View.VISIBLE
        Toast.makeText(this, "on Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
       loginResponse.observe(this, Observer {
           Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
           progress_bar.visibility = View.GONE
       })
    }

    override fun onFailure(message: String) {
        Log.d("aaa", "onFailure")
        progress_bar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
