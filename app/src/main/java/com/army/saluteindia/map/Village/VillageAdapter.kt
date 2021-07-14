package com.army.saluteindia.map.Village

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.R
import com.army.saluteindia.map.COY.CoyFragmentDirections

class VillageAdapter: RecyclerView.Adapter<VillageAdapter.MyViewHolder>() {

    var villageList = emptyList<String>()
/*
    var countList = emptyList<Int>()
*/


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val villageName: TextView = itemView.findViewById(R.id.villageName)
/*
        val noOfVillages: TextView = itemView.findViewById(R.id.noOfVillagesCOY)
*/

        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.villageConstraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.village_fragment_item, parent, false)

        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.villageName.text = villageList[position].toString()
/*
        holder.noOfVillages.text = countList[position].toString()
*/


        holder.constraintLayout.setOnClickListener {
            Navigation.findNavController(holder.constraintLayout).navigate(VillageFragmentDirections.actionVillageFragmentToMohallaFragment(villageList[position]))
        }
    }

    override fun getItemCount(): Int {
        return villageList.size
    }



    fun setData(village: List<String>){
        this.villageList = village
        notifyDataSetChanged()
    }

    /*fun setVillageCount(count: List<Int>){
        this.countList = count
        notifyDataSetChanged()
    }*/
}