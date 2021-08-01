package com.army.saluteindia.home

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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data2.PropDao
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.databinding.ActivityMainBinding
import com.army.saluteindia.databinding.FragmentHomeBinding
import com.army.saluteindia.map.COY.CoyAdapter
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_home, container, false
        )

        val dao = database.getInstance(requireContext()).dao



        binding.coy.setOnClickListener {view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_coyFragment)
        }

        binding.village.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToVillageFragment("home"))
        }

        binding.mohalla.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToMohallaFragment("home"))
        }

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.download -> {
                    setData(dao)
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDownloadProgressFragment())
                    true
                }
                R.id.upload -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUploadDocumentFragment())
                    true
                }
                R.id.search -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
                    true
                }
                else -> false
            }
        }

        /*binding.downloadDataIcon.setOnClickListener {

            lifecycleScope.launchWhenCreated {
                viewModel.getHouses()
                viewModel.getCompleteVillageList()
                viewModel.getCompleteMohallaList()
                viewModel.getCompleteHousesList()

            }
            getData(dao)
        }*/


        return binding.root
    }

    private fun setData(dao: PropDao) {
        viewModel._villagesComplete.observe(viewLifecycleOwner, Observer { list ->
            Log.i("villagesMain", list.toString())
            val villList = mutableListOf<VILLAGE>()
            list.forEach {
                val village =
                    VILLAGE(it._id, it.mohallaCount, it.houseCount, it.mohallaCount, it.coy)
                villList.add(village)
                lifecycleScope.launch {
                    dao.insertVillage(village)
                }
            }
        })

        viewModel._housesComplete.observe(viewLifecycleOwner, Observer { list ->
            Log.i("housesMain", list.toString())
            list.forEach {
                val house = HOUSES(
                    it._id,
                    it.house,
                    it.floor,
                    it.colour,
                    it.perimeterfence.toString(),
                    it.cowshed.toString(),
                    it.entryPoints.toInt(),
                    it.geo.lat,
                    it.geo.long,
                    it.property,
                    1,
                    2,
                    3,
                    4,
                    5,
                    1,
                    1,
                    it.coy,
                    it.village,
                    it.mohalla,
                    it.husbandDocument.name,
                    it.husbandDocument.tel,
                    it.husbandDocument.age
                )
                lifecycleScope.launch {
                    dao.insertHouse(house)
                }
            }
        })

        viewModel._mohallasComplete.observe(viewLifecycleOwner, Observer { list ->
            Log.i("mohallasMain", list.toString())
            list.forEach {
                val mohalla = MOHALLA(it._id, it.houseCount, it.houseCount, it.village)
                lifecycleScope.launch {
                    dao.insertMohalla(mohalla)
                }
            }
        })

        viewModel._coys.observe(viewLifecycleOwner, Observer { list ->
            Log.i("coysMain", list.toString())
            list.forEach {
                val coy = COY(it._id, it.villagesCount, it.mohallasCount, it.housesCount)
                lifecycleScope.launch {
                    dao.insertCoy(coy)
                }
            }
        })
    }

    private fun getData(){

    }


}