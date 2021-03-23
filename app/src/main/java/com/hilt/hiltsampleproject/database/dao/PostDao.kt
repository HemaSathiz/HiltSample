package com.hilt.hiltsampleproject.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hilt.hiltsampleproject.model.PostDetailsItem

@Dao
interface PostDao {
    @Transaction
    suspend fun getData(users: List<PostDetailsItem>) : List<PostDetailsItem> {
        deletePostDetails()
        insertAll(users)
        return getPostDetails()
    }

    @Insert
    suspend fun insertAll(users: List<PostDetailsItem>)

    @Query("SELECT * FROM PostDetailsItem")
    suspend fun getPostDetails(): List<PostDetailsItem>

    @Query("DELETE FROM PostDetailsItem")
    suspend fun deletePostDetails()
}
