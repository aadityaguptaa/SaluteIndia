package com.army.saluteindia.map.Village

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.R
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.relations.CoyWithVillages
import com.army.saluteindia.map.COY.CoyFragmentDirections

class VillageAdapter: RecyclerView.Adapter<VillageAdapter.MyViewHolder>() {

    var villageList = emptyList<VILLAGE>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val villageName: TextView = itemView.findViewById(R.id.villageName)
        val noOfMohallas: TextView = itemView.findViewById(R.id.noOfMohallasVillage)
        val noOfFamily: TextView = itemView.findViewById(R.id.noOfFamilyVillage)
        val noOfHouses: TextView = itemView.findViewById(R.id.noOfHousesVillage)

        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.villageConstraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.village_fragment_item, parent, false)

        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.villageName.text = villageList[position].id
        holder.noOfMohallas.text = villageList[position].mohalla_count.toString()
        holder.noOfFamily.text = villageList[position].family_count.toString()
        holder.noOfHouses.text = villageList[position].family_count.toString()

        holder.constraintLayout.setOnClickListener {
            Navigation.findNavController(holder.constraintLayout).navigate(VillageFragmentDirections.actionVillageFragmentToMohallaFragment(villageList[position].id))
        }
    }

    override fun getItemCount(): Int {
        return villageList.size
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