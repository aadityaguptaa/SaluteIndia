package com.army.saluteindia.map.Mohalla

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.R
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.databinding.FragmentMohallaBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.map.Village.VillageAdapter
import com.army.saluteindia.map.Village.VillageFragmentArgs


class MohallaFragment : Fragment() {


    private val args: MohallaFragmentArgs by navArgs()

    lateinit var binding : FragmentMohallaBinding
    lateinit var viewModel: PropertyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_mohalla, container, false
        )

        var village = args.villageName

        viewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)
        viewModel.getMohallas(village)

        var mohallaAdapter = MohallaAdapter()
        binding.mohallaFragmentRecyclerView.adapter = mohallaAdapter
        binding.mohallaFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //REPAIR THIS THREAD !!!!!!!!!!!!!!!!!!!!!!!!!!
        Thread.sleep(100)

        viewModel.mohallas.observe(viewLifecycleOwner, Observer { t ->
            mohallaAdapter.setData(t)

        })

        return binding.root
    }

}