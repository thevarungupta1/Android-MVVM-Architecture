package com.thevarungupta.androidmvvmarchitecture.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thevarungupta.androidmvvmarchitecture.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class UserRepository {

    fun userLogin(email: String, password: String): LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        MyApi().userLogin(email, password)
            .enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    loginResponse.value = t?.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body().string()
                        Log.d("aaa", "success")
                    }else{
                        loginResponse.value = response.errorBody().toString()
                        Log.d("aaa", "error")
                    }
                }

            })
        return loginResponse
    }


}