package com.army.saluteindia.map.Village

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.databinding.FragmentCoyBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.map.COY.CoyAdapter
import kotlinx.coroutines.launch


class VillageFragment : Fragment() {

    private val args: VillageFragmentArgs by navArgs()
    lateinit var binding : FragmentVillageBinding
    lateinit var viewModel: viewModel

    private val viewModel2: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_village, container, false
        )

        var coy = args.coyName

        val dao = database.getInstance(requireContext()).dao


        viewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)
        if(coy != "home") {
            viewModel.getVillages(coy)
        }

        var villageAdapter = VillageAdapter()
        binding.villageFragmentRecyclerView.adapter = villageAdapter
        binding.villageFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel2.getVillages(args.coyName)

        viewModel2._villages.observe(viewLifecycleOwner, Observer { list ->
            Log.i("asdfg", list.toString())
            val villList= mutableListOf<VILLAGE>()
            list.forEach {
                val village = VILLAGE(it._id, it.mohallaCount, it.houseCount, it.mohallaCount, coy)
                villList.add(village)
                lifecycleScope.launch {
                    dao.insertVillage(village)
                }
            }
            villageAdapter.villages = villList
        })

        Thread.sleep(100)
        viewModel.villages.observe(viewLifecycleOwner, Observer {
            villageAdapter.villages = it
        })



      return binding.root
    }


}