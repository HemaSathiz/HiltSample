package com.hilt.hiltsampleproject.app

import com.hilt.hiltsampleproject.model.Posts
import com.hilt.hiltsampleproject.network.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ApiHelperImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : ApiHelper {
    override suspend fun getPosts(): Response<Posts> {
        return apiHelper.getPosts()
    }

}