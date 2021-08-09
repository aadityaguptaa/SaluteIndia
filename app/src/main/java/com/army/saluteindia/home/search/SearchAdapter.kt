package com.army.saluteindia.home.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.army.saluteindia.data.networklogin.searchResponses.Data
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.databinding.MohallaFragmentItemBinding
import com.army.saluteindia.databinding.SearchDetailsFragmentItemBinding
import com.army.saluteindia.map.Mohalla.MohallaFragmentDirections

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    var searchList = emptyList<Data>()

    inner class MyViewHolder(val binding: SearchDetailsFragmentItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var persons: List<Data>
        get() = differ.currentList
        set(value) {differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.MyViewHolder {
        return MyViewHolder(
            SearchDetailsFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            val person = persons[position]
            personName.text = person.name
            age.text = person.age.toString()
            occupation.text = person.occupation.toString()

            searchConstraintLayout.setOnClickListener{

            }

        }

    }

    override fun getItemCount(): Int {
        return persons.size
    }



    fun setData(Persons: List<Data>){
        this.persons = Persons
        notifyDataSetChanged()
    }

    /*fun setVillageCount(count: List<Int>){
        this.countList = count
        notifyDataSetChanged()
    }*/
}