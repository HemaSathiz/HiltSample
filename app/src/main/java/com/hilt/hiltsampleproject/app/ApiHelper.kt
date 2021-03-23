package com.hilt.hiltsampleproject.app

import com.hilt.hiltsampleproject.model.Posts
import retrofit2.Response

interface ApiHelper {
    suspend fun getPosts(): Response<Posts>
}