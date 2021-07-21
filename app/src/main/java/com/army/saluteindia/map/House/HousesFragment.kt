package com.army.saluteindia.map.House

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.databinding.FragmentHousesBinding
import com.army.saluteindia.map.Mohalla.MohallaAdapter
import com.army.saluteindia.map.Mohalla.MohallaFragmentArgs
import kotlinx.coroutines.launch


class HousesFragment : Fragment() {

    lateinit var binding: FragmentHousesBinding
    private val args: HousesFragmentArgs by navArgs()

    private val viewModel2: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dao = database.getInstance(requireContext()).dao

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_houses, container, false
        )

        val houseAdapter = HouseAdapter()
        binding.housesFragmentRecyclerView.adapter = houseAdapter
        binding.housesFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel2.getHouses2(args.mohallaId)

        viewModel2._houses2.observe(viewLifecycleOwner, Observer { list ->
            Log.i("asdfg", list.toString())
            val houseList= mutableListOf<HOUSES>()
            binding.villageNameHouseMainFragment.text = list[0].village
            binding.mohallaNameHouseMainFragment.text = list[0].mohalla
            binding.companyNameHouseMainFragment.text =list[0].coy
            list.forEach {
                val house = HOUSES(it._id, it.house, it.floor, it.colour, it.perimeterfence.toString(), it.cowshed.toString(), it.entryPoints.toInt(), it.geo.lat
                , it.geo.long, it.property, 1, 2, 3, 4, 5, 1, 1, it.coy, it.village, it.mohalla, it.husbandDocument.name, it.husbandDocument.tel, it.husbandDocument.age)
                if(house.mohalla_id == args.mohallaId){
                    houseList.add(house)
                    lifecycleScope.launch {
                        dao.insertHouse(house)
                    }
                }
            }
            houseAdapter.houses = houseList
        })


        lifecycleScope.launch {
            binding.houseFragmentProgressbar.isVisible = true

            val houseList = try {
                dao.getHouseListNotLive(args.mohallaId)
            }catch(e: Exception) {
                Log.i("error", e.toString())
                binding.houseFragmentProgressbar.isVisible = false
            } as List<HOUSES>

            if(houseList != null) {
                houseAdapter.houses = houseList as List<HOUSES>
            }
            binding.villageNameHouseMainFragment.text = houseList[0].village_id
            binding.mohallaNameHouseMainFragment.text = houseList[0].mohalla_id
            binding.companyNameHouseMainFragment.text =houseList[0].coy_id
            binding.houseFragmentProgressbar.isVisible = false
        }
        return binding.root
    }


}