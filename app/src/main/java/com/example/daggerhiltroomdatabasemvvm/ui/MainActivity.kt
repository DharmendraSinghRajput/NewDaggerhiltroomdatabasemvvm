package com.example.daggerhiltroomdatabasemvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.daggerhiltroomdatabasemvvm.R
import com.example.daggerhiltroomdatabasemvvm.databinding.ActivityMainBinding
import com.example.daggerhiltroomdatabasemvvm.databinding.ActivityRegisterBinding
import com.example.daggerhiltroomdatabasemvvm.module.UserViewModel
import com.example.daggerhiltroomdatabasemvvm.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
           btnLogin.setOnClickListener {
               startActivity(Intent(this@MainActivity,HomeActivity::class.java))

               /*if (!editUserEmail.text.toString().trim().equals("") && !editUserPassword.text.toString().trim().equals("")){

                  CoroutineScope(Dispatchers.Main).launch{
                      //userViewModel.loginUser(editUserEmail.text.toString().trim()).apply {
                          showToast(it.toString())
                      }*/
                  }
               }
            //   userViewModel.loginUser()
             /*  userViewModel.loginUserReponse.observe(this@MainActivity){
                   if (it.isNullOrEmpty()){
                       startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                   }
               }*/

           }

}