package com.hilt.hiltsampleproject.repositories

import com.hilt.hiltsampleproject.database.AppDatabase
import com.hilt.hiltsampleproject.model.PostDetailsItem
import com.hilt.hiltsampleproject.network.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainRepository @Inject constructor(
    private val apiHelper: ApiService,
    private val database: AppDatabase
) {
    suspend fun getPostDetails() = apiHelper.getPostDetails()
    suspend fun getLocalOfflinePostDetails(posts: List<PostDetailsItem>) = database.postDao().getData(posts)
}
