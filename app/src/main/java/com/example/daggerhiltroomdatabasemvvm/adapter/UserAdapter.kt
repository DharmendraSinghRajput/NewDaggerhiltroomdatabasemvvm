package com.example.daggerhiltroomdatabasemvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltroomdatabasemvvm.databinding.RowUserBinding
import com.example.daggerhiltroomdatabasemvvm.table.HomeProfileTable
import com.example.daggerhiltroomdatabasemvvm.utils.GeneralFunctions

class UserAdapter(val onClick: (HomeProfileTable) -> Unit) : RecyclerView.Adapter<UserAdapter.SportViewHolder>() {

    private var userProfile = listOf<HomeProfileTable>()

    inner class SportViewHolder(val mBinding: RowUserBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder = SportViewHolder(RowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = userProfile.size

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.mBinding.apply {
            userProfile[position].apply {
                textUserName.text=userProfile[position].name
                textPhoneNumber.text=userProfile[position].contact_number
                GeneralFunctions.loadImage(root.context,userProfile[position].image ?: "",imgProfile)
            }
            root.setOnClickListener {
                onClick(userProfile[position])
            }

        }
    }

    fun setData(profileList: List<HomeProfileTable>) {
        this.userProfile = profileList
        notifyDataSetChanged()
    }
}