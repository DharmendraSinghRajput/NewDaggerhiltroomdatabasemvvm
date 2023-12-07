package com.example.daggerhiltroomdatabasemvvm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.daggerhiltroomdatabasemvvm.table.HomeProfileTable
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDetailsTable: UserDetailsTable):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userProfileTable: HomeProfileTable):Long


    @Query("SELECT * FROM user_profile ORDER BY profileId ASC")
    fun getUserDataProfile():Flow<List<HomeProfileTable>>

    @Update
    fun updateUserHome(homeProfileTable: HomeProfileTable)

      @Query("SELECT * FROM user_table ORDER BY userId ASC")
     fun getUserData():Flow<List<UserDetailsTable>>


}