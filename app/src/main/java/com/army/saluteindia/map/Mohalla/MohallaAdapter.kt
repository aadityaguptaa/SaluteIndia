package com.army.saluteindia.map.Mohalla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.R
import com.army.saluteindia.data2.entities.MOHALLA

class MohallaAdapter: RecyclerView.Adapter<MohallaAdapter.MyViewHolder>() {

    var mohallaList = emptyList<MOHALLA>()
/*
    var countList = emptyList<Int>()
*/


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mohallaName: TextView = itemView.findViewById(R.id.mohallaName)
        val familyCount: TextView = itemView.findViewById(R.id.noOfFamilyMohalla)
        val houseCount: TextView = itemView.findViewById(R.id.noOfHouseMohalla)

/*
        val noOfVillages: TextView = itemView.findViewById(R.id.noOfVillagesCOY)
*/

        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.mohallaConstraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.mohalla_fragment_item, parent, false)

        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mohallaName.text = mohallaList[position].id
        holder.familyCount.text = mohallaList[position].family_count.toString()
        holder.houseCount.text = mohallaList[position].family_count.toString()

        holder.constraintLayout.setOnClickListener {
            Navigation.findNavController(holder.constraintLayout).navigate(MohallaFragmentDirections.actionMohallaFragmentToHousesFragment(mohallaList[position].id))
        }
    }

    override fun getItemCount(): Int {
        return mohallaList.size
    }



    fun setData(mohalla: List<MOHALLA>){
        this.mohallaList = mohalla
        notifyDataSetChanged()
    }

    /*fun setVillageCount(count: List<Int>){
        this.countList = count
        notifyDataSetChanged()
    }*/
}