package com.hilt.hiltsampleproject.repositories

import com.hilt.hiltsampleproject.app.ApiHelper
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){
    suspend fun getPostDetails() = apiHelper.getPosts()
}