package com.army.saluteindia.map.Village

import android.util.Log
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
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.relations.CoyWithVillages
import com.army.saluteindia.databinding.HouseLayoutItemBinding
import com.army.saluteindia.databinding.MohallaFragmentItemBinding
import com.army.saluteindia.databinding.VillageFragmentItemBinding
import com.army.saluteindia.map.COY.CoyFragmentDirections
import com.army.saluteindia.map.House.HouseAdapter
import com.army.saluteindia.map.Mohalla.MohallaAdapter
import com.army.saluteindia.map.Mohalla.MohallaFragmentDirections

class VillageAdapter: RecyclerView.Adapter<VillageAdapter.MyViewHolder>() {

    var villageList = emptyList<VILLAGE>()


    inner class MyViewHolder(val binding: VillageFragmentItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<VILLAGE>(){
        override fun areItemsTheSame(oldItem: VILLAGE, newItem: VILLAGE): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VILLAGE, newItem: VILLAGE): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var villages: List<VILLAGE>
        get() = differ.currentList
        set(value) {differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VillageAdapter.MyViewHolder {
        return MyViewHolder(
            VillageFragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: VillageAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            val village = villages[position]
            villageName.text = village.id
            noOfMohallasVillage.text = village.mohalla_count.toString()
            noOfFamilyVillage.text = village.family_count.toString()
            noOfHousesVillage.text = village.family_count.toString()

            villageConstraintLayout.setOnClickListener{
                Navigation.findNavController(it).navigate(VillageFragmentDirections.actionVillageFragmentToMohallaFragment(village.id, village.coy_id))

            }

        }

    }

    override fun getItemCount(): Int {
        return villages.size
    }



    fun setData(village: List<VILLAGE>){
        this.villageList = village
        notifyDataSetChanged()
    }

    /*fun setVillageCount(count: List<Int>){
        this.countList = count
        notifyDataSetChanged()
    }*/
}