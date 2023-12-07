package com.example.daggerhiltroomdatabasemvvm.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDetailsTable(
    @PrimaryKey(autoGenerate = true)
    val userId:Int=0,
    val name:String="",
    val email:String="",
    val password:String="",
    val address:String=""
)