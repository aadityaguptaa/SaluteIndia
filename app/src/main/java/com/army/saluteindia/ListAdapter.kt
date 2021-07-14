package com.army.saluteindia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.data.Property

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var propertyList = emptyList<Property>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstName: TextView = itemView.findViewById(R.id.firstNameText)
        val lastName: TextView = itemView.findViewById(R.id.lastNameText)
        val age: TextView = itemView.findViewById(R.id.ageText)
        val id: TextView = itemView.findViewById(R.id.idText)
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.rowConstraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)

        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.firstName.text = propertyList[position].Name
        holder.lastName.text = propertyList[position].Village
        holder.age.text = propertyList[position].Age.toString()
        holder.id.text = propertyList[position].id.toString()
        var latlong = propertyList[position].latlong.split('/')
        var lat = latlong[0].toFloat()
        var long = latlong[1].toFloat()

        /*holder.constraintLayout.setOnClickListener {
            Navigation.findNavController(holder.constraintLayout).navigate(ListFragmentDirections.actionListFragmentToMapsFragment(lat, long))
        }*/
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }



    fun setData(property: List<Property>){
        this.propertyList = property
        notifyDataSetChanged()
    }
}