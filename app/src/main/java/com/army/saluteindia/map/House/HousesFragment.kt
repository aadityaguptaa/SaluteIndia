package com.army.saluteindia.map.House

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
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.CoyRepository
import com.army.saluteindia.data.repository.HouseRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.databinding.FragmentCoyBinding
import com.army.saluteindia.databinding.FragmentHousesBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.map.COY.CoyAdapter
import com.army.saluteindia.map.COY.CoyViewModel
import com.army.saluteindia.map.Mohalla.MohallaAdapter
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.visible
import khttp.get
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


class HousesFragment : BaseFragment<HouseViewModel, FragmentHousesBinding, HouseRepository>() {

    private val args: HousesFragmentArgs by navArgs()
    lateinit var houseAdapter: HouseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.houseFragmentProgressbar.visible(false)
        var mohalla = args.mohallaId
        var viewModel3 = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)

        binding.hfVillageName.text = args.villageId
        binding.hfMohallaName.text = args.mohallaId

        /*viewModel3.getVillages(args.mohallaId)
        Thread.sleep(100)
        viewModel3.houses.observe(viewLifecycleOwner, Observer { list ->
            houseAdapter.houses = list
        })*/

        if(isInternetConnection()){
            if(mohalla == "None"){
                viewModel.getHouses(mohalla)

            }else{

                viewModel.getHouses(mohalla)
            }
        }else{
            viewModel3.getHouses(mohalla)
            Thread.sleep(100)
            Log.i("asdf", "no connection")
            viewModel3.houses.observe(viewLifecycleOwner, Observer { list ->
                houseAdapter.houses = list
            })
        }


        viewModel.getHouses(mohalla)
        addObserver()
        houseAdapter = HouseAdapter()

        binding.housesFragmentRecyclerView.adapter = houseAdapter
        binding.housesFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.hfCompanyName.text = args.companyName

    }

    override fun getViewModel() = HouseViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHousesBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): HouseRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return HouseRepository(api, userPreferences)
    }

    fun addObserver(){
        val dao = database.getInstance(requireContext()).dao

        viewModel._housesComplete.observe(viewLifecycleOwner, Observer {
            binding.houseFragmentProgressbar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    val houseList= mutableListOf<HOUSES>()
                    it.value.data.forEach {
                        val house = HOUSES(it._id, it.house, it.floor, it.colour, it.perimeterfence.toString(), it.cowshed.toString(), it.entryPoints.toInt(), it.geo.lat, it.geo.long, it.property, 1, 2, 3, 4, 1, 2, 1, it.coy, it.village, it.mohalla, it.husbandDocument.name, it.husbandDocument.tel, it.husbandDocument.age)
                        houseList.add(house)
                        lifecycleScope.launch {
                            dao.insertHouse(house)
                        }
                    }
                    binding.noOfHousesHouseMainFragment.text = houseList.size.toString()
                    houseAdapter.houses =houseList
                }
                is Resource.failure -> handleApiError(it){

                }
            }
        })


    }

    fun isInternetConnection(): Boolean {
        var returnVal = false
        thread {
            returnVal = try {
                get("https://www.google.com/")
                true
            }catch (e:Exception){
                false
            }
        }.join()
        return returnVal
    }


}