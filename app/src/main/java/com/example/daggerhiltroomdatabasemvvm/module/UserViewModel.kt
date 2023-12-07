package com.example.daggerhiltroomdatabasemvvm.module

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltroomdatabasemvvm.room.UserDetailsTable
import com.example.daggerhiltroomdatabasemvvm.room.UserRepository
import com.example.daggerhiltroomdatabasemvvm.table.HomeProfileTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {


     var itemsLiveDataResponse=MutableLiveData<List<UserDetailsTable>>()

    var userLiveDataProfileResponse=MutableLiveData<List<HomeProfileTable>>()

    fun getUserData(): Flow<List<UserDetailsTable>> = userRepository.getUserDatabase
    init {
        viewModelScope.launch {
            userRepository.getUserDatabase.collect {
                itemsLiveDataResponse.postValue(it)
            }
            //   itemsLiveDataResponse.postValue(userRepository.getUserDatabase)
        }
        viewModelScope.launch {
            userRepository.getUserDatabaseProfile.collect {
                userLiveDataProfileResponse.postValue(it)
            }

        }
    }


    fun insertUser(userDetailsTable: UserDetailsTable) = viewModelScope.launch {
        userRepository.insert(userDetailsTable)
    }

    fun insertData(userProfileTable: HomeProfileTable) =viewModelScope.launch {
        userRepository.insertProfile(userProfileTable)
    }
    fun updateProfileHome(userProfileTable: HomeProfileTable) =viewModelScope.launch {
        userRepository.updateProfileHome(userProfileTable)
    }


  // var loginUserReponse=MutableLiveData<List<UserDetailsTable>>()

/*    suspend fun loginUser(email: String) {
        loginUserReponse=userRepository.loginUser(email)

    }*/

}