package com.hilt.hiltsampleproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentsItem(
    val body: String,
    val email: String,
    @PrimaryKey val id: Int,
    val name: String,
    val postId: Int,
    val favourites: Boolean = false
)
