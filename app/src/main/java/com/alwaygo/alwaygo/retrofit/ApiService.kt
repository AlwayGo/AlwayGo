package com.alwaygo.alwaygo.retrofit

import com.alwaygo.alwaygo.data.remote.model.ImageModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v2/list")
    fun getImages(): Call<List<ImageModel>>
}