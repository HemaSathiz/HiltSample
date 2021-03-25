package com.hilt.hiltsampleproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostDetailsItem(
    val body: String,
    @PrimaryKey val id: Int,
    val title: String,
    val userId: Int
)