package com.army.saluteindia.map.COY

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.R
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.network.coys.CoyItem

class CoyAdapter: RecyclerView.Adapter<CoyAdapter.MyViewHolder>() {

    var coyList = emptyList<COY>()



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val coyName: TextView = itemView.findViewById(R.id.companyName)
        val noOfVillages: TextView = itemView.findViewById(R.id.noOfVillagesCoy)
        val noOfMohallas: TextView = itemView.findViewById(R.id.noOfMohallasCoy)
        val noOfHouses: TextView = itemView.findViewById(R.id.noOfHousesCoy)

        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.coyConstraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.coy_fragment_item, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.coyName.text = coyList[position]._id
        holder.noOfVillages.text = coyList[position].villagesCount.toString()
        holder.noOfMohallas.text = coyList[position].mohallasCount.toString()
        holder.noOfHouses.text = coyList[position].housesCount.toString()

        holder.constraintLayout.setOnClickListener {
            Navigation.findNavController(holder.constraintLayout).navigate(CoyFragmentDirections.actionCoyFragmentToVillageFragment(coyList[position]._id))
        }
    }

    override fun getItemCount(): Int {
        return coyList.size
    }



    fun setData(coy: List<COY>?){

        if (coy != null) {
            this.coyList = coy
        }
        notifyDataSetChanged()

    }


}