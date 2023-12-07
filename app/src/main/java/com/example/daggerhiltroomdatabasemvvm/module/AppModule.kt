package com.example.daggerhiltroomdatabasemvvm.module

import android.content.Context
import androidx.room.Room
import com.example.daggerhiltroomdatabasemvvm.room.UserDatabase
import com.example.daggerhiltroomdatabasemvvm.room.UserDetailsDao
import com.example.daggerhiltroomdatabasemvvm.room.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): UserDatabase=
        Room.databaseBuilder(context,UserDatabase::class.java,"userDatabase").build()


    @Provides
    fun providesUserDao(userDatabase: UserDatabase):UserDetailsDao=userDatabase.userDao()


    @Provides
    fun providesUserRepository(userDetailsDao: UserDetailsDao):UserRepository=UserRepository(userDetailsDao)

}