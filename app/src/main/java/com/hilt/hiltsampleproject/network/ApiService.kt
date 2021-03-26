package com.hilt.hiltsampleproject.network

import com.hilt.hiltsampleproject.model.Comments
import com.hilt.hiltsampleproject.model.Posts
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    suspend fun getPostDetails(): Posts

    @GET("/comments?")
    suspend fun getCommentsDetails(@Query("postId")postId: Int?): Comments
}
