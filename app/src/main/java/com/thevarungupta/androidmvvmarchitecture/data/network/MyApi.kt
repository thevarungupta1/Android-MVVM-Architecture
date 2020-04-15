package com.thevarungupta.androidmvvmarchitecture.data.network

import com.thevarungupta.androidmvvmarchitecture.app.Config
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("auth/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    companion object{
        operator fun invoke(): MyApi {
            return Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }


}