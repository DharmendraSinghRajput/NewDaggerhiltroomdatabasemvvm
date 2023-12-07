package com.example.daggerhiltroomdatabasemvvm.table

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_profile")
data class HomeProfileTable(
    @PrimaryKey(autoGenerate = true)
    val profileId: Int,
    val name: String="",
    val contact_number: String ="",
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: Bitmap
)