package com.hilt.hiltsampleproject.network

import com.hilt.hiltsampleproject.model.Posts
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPostDetails(): Posts
}