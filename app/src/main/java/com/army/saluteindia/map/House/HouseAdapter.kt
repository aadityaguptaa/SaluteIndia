package com.army.saluteindia.map.House

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
import com.army.saluteindia.map.Mohalla.MohallaAdapter
import com.army.saluteindia.map.Mohalla.MohallaFragmentDirections

class HouseAdapter: RecyclerView.Adapter<HouseAdapter.MyViewHolder>()  {

    var houseList = emptyList<HOUSES>()


    inner class MyViewHolder(val binding: HouseLayoutItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<HOUSES>(){
        override fun areItemsTheSame(oldItem: HOUSES, newItem: HOUSES): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HOUSES, newItem: HOUSES): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var houses: List<HOUSES>
        get() = differ.currentList
        set(value) {differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(HouseLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
        /*var item = LayoutInflater.from(parent.context).inflate(R.layout.house_layout_item, parent, false)

        return MyViewHolder(item)*/
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val house = houses[position]
            headNameHouseFragment.text = house.husbandName
            houseNumberHouseFragment.text = house.house
            mobileNumberHouseFragment.text = house.mobileNumber
            ageHouseFragment.text = house.age

            houseConstraintLayout.setOnClickListener{
                Navigation.findNavController(it).navigate(HousesFragmentDirections.actionHousesFragmentToDetailsFragment(houses[position]))
            }

        }

    }

    override fun getItemCount() = houses.size


    fun setData(houses: List<HOUSES>){
        this.houseList = houses
        notifyDataSetChanged()
    }

}