package com.army.saluteindia.map.Mohalla

import android.content.Context
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
import com.army.saluteindia.data.repository.MohallaRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.databinding.FragmentCoyBinding
import com.army.saluteindia.databinding.FragmentMohallaBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.map.COY.CoyAdapter
import com.army.saluteindia.map.COY.CoyViewModel
import com.army.saluteindia.map.Mohalla.MohallaAdapter
import com.army.saluteindia.map.Mohalla.MohallaFragmentArgs
import com.army.saluteindia.map.Mohalla.MohallaViewModel
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import android.net.NetworkInfo

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import kotlin.concurrent.thread


class MohallaFragment : BaseFragment<MohallaViewModel, FragmentMohallaBinding, MohallaRepository>() {

    private val args: MohallaFragmentArgs by navArgs()
    private lateinit var mohallaAdapter: MohallaAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mfProgressbar.visible(false)
        val village = args.villageId
        val viewModel3 = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)


        Log.i("home", args.companyName)

        if(isInternetConnection()){
            if(village == "None"){
                viewModel.getMohallas()

            }else{

                viewModel.getMohallas(village)
            }
        }else{
            viewModel3.getMohallas(village)
            Thread.sleep(100)
            Log.i("asdf", "no connection")
            viewModel3.mohallas.observe(viewLifecycleOwner, Observer { list ->
                mohallaAdapter.mohallas = list
            })
        }

        //@TODO make it work offline
        addObserver()
        mohallaAdapter = MohallaAdapter()

        binding.mohallaFragmentRecyclerView.adapter = mohallaAdapter
        binding.mohallaFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mohallaAdapter.setCompany(args.companyName)

    }

    override fun getViewModel() =MohallaViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMohallaBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): MohallaRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return MohallaRepository(api, userPreferences)
    }

    private fun addObserver(){
        val dao = database.getInstance(requireContext()).dao

        viewModel._mohallasComplete.observe(viewLifecycleOwner, Observer { list ->
            binding.mfProgressbar.visible(list is Resource.loading)
            when(list){
                is Resource.success -> {
                    val mohallaList= mutableListOf<MOHALLA>()
                    list.value.data.forEach {
                        val mohalla = MOHALLA(it._id, it.houseCount, it.houseCount, args.villageId)
                        mohallaList.add(mohalla)
                        lifecycleScope.launch {
                            dao.insertMohalla(mohalla)
                        }
                    }
                    mohallaAdapter.mohallas = mohallaList
                }
                is Resource.failure -> handleApiError(list){

                }
                Resource.loading -> TODO()
            }
        })


    }

    private fun isInternetConnection(): Boolean {
        var returnVal = false
        thread {
            returnVal = try {
                khttp.get("https://www.google.com/")
                true
            }catch (e:Exception){
                false
            }
        }.join()
        return returnVal
    }


}