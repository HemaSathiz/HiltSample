package com.hilt.hiltsampleproject.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hilt.hiltsampleproject.model.CommentsItem

@Dao
interface CommentsDao {

    @Transaction
    suspend fun getData(users: List<CommentsItem>): List<CommentsItem> {
        insertAll(users)
        return getCommentDetails()
    }

    @Insert
    suspend fun insertAll(users: List<CommentsItem>)

    @Query("SELECT * FROM CommentsItem")
    suspend fun getCommentDetails(): List<CommentsItem>

    @Query("DELETE FROM CommentsItem")
    suspend fun deleteCommentDetails()
}
