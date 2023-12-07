package com.example.daggerhiltroomdatabasemvvm.room

import androidx.annotation.WorkerThread
import com.example.daggerhiltroomdatabasemvvm.table.HomeProfileTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository constructor(private val userDao: UserDetailsDao) {

    val getUserDatabase: Flow<List<UserDetailsTable>> = userDao.getUserData()

    val getUserDatabaseProfile: Flow<List<HomeProfileTable>> = userDao.getUserDataProfile()

    /*    suspend fun insert(userDetailsTable: UserDetailsTable):Flow<Resource<UserDetailsTable>>{
            return flow {
                val result= userDao.insert(userDetailsTable)
               // Log.d("ResponseDataUser","${result}")
               // showToast("Fill the all Field")

                // emit(Resource.))
            }.flowOn(Dispatchers.IO)
        }*/


    suspend fun insert(userDetailsTable: UserDetailsTable) = withContext(Dispatchers.IO) {
        userDao.insert(userDetailsTable)
    }

    suspend fun insertProfile(userProfileTable: HomeProfileTable) = withContext(Dispatchers.IO){
        userDao.insertUser(userProfileTable)
    }


    @WorkerThread
    suspend fun updateProfileHome(userProfileTable: HomeProfileTable) = withContext(Dispatchers.IO){
        userDao.updateUserHome(userProfileTable)
    }



}