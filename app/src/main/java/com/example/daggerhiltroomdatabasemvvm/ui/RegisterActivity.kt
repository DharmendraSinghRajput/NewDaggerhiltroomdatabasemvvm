package com.example.daggerhiltroomdatabasemvvm.ui

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerhiltroomdatabasemvvm.databinding.ActivityRegisterBinding
import com.example.daggerhiltroomdatabasemvvm.module.UserViewModel
import com.example.daggerhiltroomdatabasemvvm.room.UserDetailsTable
import com.example.daggerhiltroomdatabasemvvm.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val userViewModel by viewModels<UserViewModel>()

    val mBinding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            userViewModel.itemsLiveDataResponse.observe(this@RegisterActivity){
                if (!it.isNullOrEmpty()){
                    showToast(it.toString())
                }
            }

            btnSaveButton.setOnClickListener {
                if (!editUserName.text.toString().equals("") && !editUserEmail.text.toString()
                        .equals("") && !editUserPassword.text.toString()
                        .equals("") && !editUserAddress.text.toString().equals("")) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val userDetailsTable = UserDetailsTable(
                            name = editUserName.text.toString(),
                            email =editUserEmail.text.toString(),
                            password = editUserPassword.text.toString(),
                            address = editUserAddress.text.toString())
                        userViewModel.insertUser(userDetailsTable)
                    }

                } else {
                    showToast("Fill the all Field")

                }
            }

        }
    }

}