package com.example.daggerhiltroomdatabasemvvm.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerhiltroomdatabasemvvm.adapter.UserAdapter
import com.example.daggerhiltroomdatabasemvvm.databinding.ActivityHomeBinding
import com.example.daggerhiltroomdatabasemvvm.databinding.RowDialogBoxBinding
import com.example.daggerhiltroomdatabasemvvm.module.UserViewModel
import com.example.daggerhiltroomdatabasemvvm.table.HomeProfileTable
import com.example.daggerhiltroomdatabasemvvm.utils.showToast
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    val mBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    var rowDialogBoxBinding: RowDialogBoxBinding? = null
    private val userViewModel by viewModels<UserViewModel>()
    var uri: Uri? = null
    var image: Bitmap? = null
    private val userAdapter by lazy {
        UserAdapter { position ->
            alertDialogBox(position)
            rowDialogBoxBinding?.editTextName?.setText(position.name)
            rowDialogBoxBinding?.editPhoneNumber?.setText(position.contact_number)
            rowDialogBoxBinding?.imgProfile?.setImageBitmap(position.image)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            ButtonFloating.setOnClickListener {
                alertDialogBox()
            }

            userViewModel.userLiveDataProfileResponse.observe(this@HomeActivity) {
               userAdapter.setData(it as ArrayList<HomeProfileTable>)

            }
            listRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = userAdapter
            }
        }
    }

    private fun alertDialogBox(homeProfileTable: HomeProfileTable? = null) {
        val dialog = Dialog(this)
        rowDialogBoxBinding = RowDialogBoxBinding.inflate(layoutInflater)
        dialog.setContentView(rowDialogBoxBinding?.root!!)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(650, 1000)
        dialog.show()

       rowDialogBoxBinding?.imgProfile?.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

        rowDialogBoxBinding?.iconId?.setOnClickListener {
            dialog.dismiss()
            rowDialogBoxBinding?.editTextName?.setText("")
            rowDialogBoxBinding?.editPhoneNumber?.setText("")
            rowDialogBoxBinding?.imgProfile?.setImageBitmap(null)

        }
        rowDialogBoxBinding?.buttonSave?.setOnClickListener {
            val userName = rowDialogBoxBinding?.editTextName?.text.toString().trim()
            val userNumber = rowDialogBoxBinding?.editPhoneNumber?.text.toString().trim()
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userNumber)) {
                if(image!=null) {
                        val userProfileTable = HomeProfileTable(profileId = 0,name = userName, contact_number = userNumber, image = image!!)
                        if (homeProfileTable==null) {
                            userViewModel.insertData(userProfileTable)
                            showToast("Record Insert")
                            dialog.dismiss()
                        }else{
                           val userProfileTableUpdate = HomeProfileTable(profileId = homeProfileTable!!.profileId,name = userName, contact_number = userNumber, image = image!!)
                            userViewModel.updateProfileHome(userProfileTableUpdate)
                            showToast("Update Record")
                            dialog.dismiss()
                        }
                }
                else
                {
                    showToast("Please Select Profile")
                }
            } else {
                showToast("Please fill all the fields")
            }
        }
    }
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            uri = data?.data!!
            image = MediaStore.Images.Media.getBitmap(this.contentResolver, this.uri) as Bitmap?
            rowDialogBoxBinding?.imgProfile?.setImageBitmap(image)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            showToast(ImagePicker.getError(data))
        } else {
            showToast("Task Cancelled")
        }
    }

}