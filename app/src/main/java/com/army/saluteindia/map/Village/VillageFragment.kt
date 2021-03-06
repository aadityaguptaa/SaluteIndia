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
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.CoyRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.databinding.FragmentCoyBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.map.COY.CoyAdapter
import com.army.saluteindia.map.COY.CoyViewModel
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.visible
import khttp.get
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


class VillageFragment : BaseFragment<VillageViewModel, FragmentVillageBinding, VillageRepository>() {

    private val args: VillageFragmentArgs by navArgs()
    private lateinit var villageAdapter: VillageAdapter
    private lateinit var mainViewModel: viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vfProgressbar.visible(false)
        val coy = args.coyName

        Log.d("village", coy)
        val dao = database.getInstance(requireContext()).dao

        mainViewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)

        villageAdapter = VillageAdapter()

        Log.d("village", isInternetConnection().toString())
        if(isInternetConnection()){
            if(coy == "home"){
                viewModel.getVillages()
                Log.d("village", "coy == home executed")


            }else{
                viewModel.getVillages(coy)
                Log.d("village", "coy == coy executed")

            }
        }else{
            if(coy != "home"){
                mainViewModel.getVillages(coy)
                Thread.sleep(100)
            }
            mainViewModel.villages.observe(viewLifecycleOwner, Observer { list ->
                Log.d("village", list.toString())
                villageAdapter.villages = list
            })
        }


        addObserver()

        binding.villageFragmentRecyclerView.adapter = villageAdapter
        binding.villageFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun getViewModel() = VillageViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVillageBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): VillageRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return VillageRepository(api, userPreferences)
    }

    private fun addObserver(){
        val dao = database.getInstance(requireContext()).dao

        viewModel._villagesComplete.observe(viewLifecycleOwner, Observer {
            binding.vfProgressbar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {

                    val villList= mutableListOf<VILLAGE>()
                    it.value.data.forEach {
                        val village = VILLAGE(it._id, it.mohallaCount, it.houseCount, it.mohallaCount, args.coyName)
                        villList.add(village)
                        lifecycleScope.launch {
                            dao.insertVillage(village)
                            Log.d("village", mainViewModel.villages.value.toString())
                        }
                    }
                    villageAdapter.villages = villList
                }
                is Resource.failure -> handleApiError(it){

                }
            }
        })


    }

    private fun isInternetConnection(): Boolean {
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