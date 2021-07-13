package com.army.saluteindia.map.COY

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.ListAdapter
import com.army.saluteindia.R
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.databinding.FragmentCoyBinding


class CoyFragment : Fragment() {

    lateinit var binding : FragmentCoyBinding
    lateinit var viewModel: PropertyViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_coy, container, false
        )

        viewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)

        var coyAdapter = CoyAdapter()
        binding.coyFragmentRecyclerView.adapter = coyAdapter
        binding.coyFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.coyList.observe(viewLifecycleOwner, Observer { list ->
            coyAdapter.setData(list)
        })

       /* viewModel.villageCountList.observe(viewLifecycleOwner, Observer { list ->
            coyAdapter.setVillageCount(list)
        })*/


        return binding.root
    }


}