package com.hilt.hiltsampleproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class PostDetailsItem(
    val body: String,
    @PrimaryKey val id: Int,
    val title: String,
    val userId: Int
) : Parcelable
