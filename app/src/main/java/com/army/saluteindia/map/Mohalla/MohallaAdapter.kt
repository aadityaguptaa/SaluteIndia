package com.army.saluteindia.map.Mohalla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.R
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.databinding.HouseLayoutItemBinding
import com.army.saluteindia.databinding.MohallaFragmentItemBinding
import com.army.saluteindia.map.House.HouseAdapter
import com.army.saluteindia.map.House.HousesFragmentDirections

class MohallaAdapter: RecyclerView.Adapter<MohallaAdapter.MyViewHolder>() {

    var mohallaList = emptyList<MOHALLA>()
    var companyName = "None"

    inner class MyViewHolder(val binding: MohallaFragmentItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<MOHALLA>(){
        override fun areItemsTheSame(oldItem: MOHALLA, newItem: MOHALLA): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MOHALLA, newItem: MOHALLA): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var mohallas: List<MOHALLA>
        get() = differ.currentList
        set(value) {differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MohallaAdapter.MyViewHolder {
        return MyViewHolder(
            MohallaFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MohallaAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            val mohalla = mohallas[position]
            mohallaName.text = mohalla.id
            noOfFamilyMohalla.text = mohalla.family_count.toString()
            noOfHouseMohalla.text = mohalla.family_count.toString()

            mohallaConstraintLayout.setOnClickListener{
                Navigation.findNavController(it).navigate(MohallaFragmentDirections.actionMohallaFragmentToHousesFragment(mohalla.id, mohalla.village_id, companyName))

            }

        }

    }

    override fun getItemCount(): Int {
        return mohallas.size
    }



    fun setData(mohalla: List<MOHALLA>){
        this.mohallaList = mohalla
        notifyDataSetChanged()
    }

    fun setCompany(companyName:String){
        this.companyName = companyName
    }

    /*fun setVillageCount(count: List<Int>){
        this.countList = count
        notifyDataSetChanged()
    }*/
}