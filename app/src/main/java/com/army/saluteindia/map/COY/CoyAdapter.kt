package com.army.saluteindia.map.COY

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.R

class CoyAdapter: RecyclerView.Adapter<CoyAdapter.MyViewHolder>() {

    var coyList = emptyList<String>()
/*
    var countList = emptyList<Int>()
*/
     var p:Int=-1

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val coyName: TextView = itemView.findViewById(R.id.companyName)
/*
        val noOfVillages: TextView = itemView.findViewById(R.id.noOfVillagesCOY)
*/

        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.coyConstraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.coy_fragment_item, parent, false)

        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.coyName.text = coyList[position].toString()
/*
        holder.noOfVillages.text = countList[position].toString()
*/


        holder.constraintLayout.setOnClickListener {
            Navigation.findNavController(holder.constraintLayout).navigate(CoyFragmentDirections.actionCoyFragmentToVillageFragment(coyList[position].toString()))
        }
    }

    override fun getItemCount(): Int {
        return coyList.size
    }



    fun setData(coy: List<String>){
        this.coyList = coy
        notifyDataSetChanged()
    }

    /*fun setVillageCount(count: List<Int>){
        this.countList = count
        notifyDataSetChanged()
    }*/
}