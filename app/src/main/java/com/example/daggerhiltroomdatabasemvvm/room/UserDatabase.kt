package com.example.daggerhiltroomdatabasemvvm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.daggerhiltroomdatabasemvvm.table.HomeProfileTable
import com.example.daggerhiltroomdatabasemvvm.utils.ImageConverter

@Database(entities = [UserDetailsTable::class,HomeProfileTable::class], version=8)
@TypeConverters(ImageConverter::class)
abstract class UserDatabase :RoomDatabase(){
    abstract fun userDao():UserDetailsDao
}