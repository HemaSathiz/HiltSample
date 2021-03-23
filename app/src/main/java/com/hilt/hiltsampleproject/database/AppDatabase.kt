package com.hilt.hiltsampleproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hilt.hiltsampleproject.database.dao.PostDao
import com.hilt.hiltsampleproject.model.PostDetailsItem

@Database(entities = [PostDetailsItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}