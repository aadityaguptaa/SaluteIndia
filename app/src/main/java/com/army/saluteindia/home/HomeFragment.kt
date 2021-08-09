package com.army.saluteindia.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.networklogin.UserApi
import com.army.saluteindia.data.repository.HomeRepository
import com.army.saluteindia.data.repository.UserRepository
import com.army.saluteindia.data2.PropDao
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.databinding.FragmentHomeBinding
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.startNewActivity
import com.army.saluteindia.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, HomeRepository>() {

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): HomeRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return HomeRepository(api, userPreferences)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeFragmentProgressBar.visible(false)

        binding.coy.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_coyFragment)
        }

        binding.search.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
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
                    loadData()
                    true
                }
                R.id.upload -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUploadDocumentFragment())
                    true
                }
                R.id.logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
        binding.hfAddHouseFloatButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddHouseFragment())
        }

    }

    private fun loadData() {
        val dao = database.getInstance(requireContext()).dao

        viewModel.getVillages()
        viewModel.getCoys()
        viewModel.getMohallas()
        viewModel.getHouses()

        setData(dao)

    }

    private fun setData(dao: PropDao) {
        viewModel._villagesComplete.observe(viewLifecycleOwner, Observer {
            binding.homeFragmentProgressBar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    val villList = mutableListOf<VILLAGE>()
                    Log.i("asdf", it.value.data.size.toString())
                    it.value.data.forEach {
                        val village =
                            VILLAGE(it._id, it.mohallaCount, it.houseCount, it.mohallaCount, it.coy)
                        villList.add(village)
                        lifecycleScope.launch {
                            dao.insertVillage(village)
                        }
                    }
                }
                is Resource.failure -> handleApiError(it){  Log.i("asdf", "failure")}
            }
        })

        viewModel._mohallasComplete.observe(viewLifecycleOwner, Observer {
            binding.homeFragmentProgressBar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    it.value.data.forEach {
                        val mohalla = MOHALLA(it._id, it.houseCount, it.houseCount, it.village)
                        lifecycleScope.launch {
                            dao.insertMohalla(mohalla)
                        }
                    }
                }
                is Resource.failure -> handleApiError(it){  Log.i("asdf", "failure")}
            }
        })

        viewModel._housesComplete.observe(viewLifecycleOwner, Observer {
            binding.homeFragmentProgressBar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    it.value.data.forEach {
                        val house = HOUSES(it._id, it.house, it.floor, it.colour, it.perimeterfence.toString(), it.cowshed.toString(), it.entryPoints.toInt(), it.geo.lat, it.geo.long, it.property, 1, 2,
                            3, 4, 5, 1, 1, it.coy, it.village, it.mohalla, it.husbandDocument.name, it.husbandDocument.tel, it.husbandDocument.age
                        )
                        lifecycleScope.launch {
                            dao.insertHouse(house)
                        }
                    }
                }
                is Resource.failure -> handleApiError(it){  Log.i("asdf", "failure")}
            }
        })

        viewModel._coysComplete.observe(viewLifecycleOwner, Observer {
            binding.homeFragmentProgressBar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    it.value.data.coy.forEach {
                        val coy = COY(it._id, it.villagesCount, it.mohallasCount, it.housesCount)
                        lifecycleScope.launch {
                            dao.insertCoy(coy)
                        }
                    }
                }
                is Resource.failure -> handleApiError(it){  Log.i("asdf", "failure")}
            }
        })


    }

}