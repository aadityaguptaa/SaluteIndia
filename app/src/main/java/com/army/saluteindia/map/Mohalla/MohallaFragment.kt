package com.army.saluteindia.map.Mohalla

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.MohallaRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.databinding.FragmentMohallaBinding
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import kotlin.concurrent.thread


class MohallaFragment : BaseFragment<MohallaViewModel, FragmentMohallaBinding, MohallaRepository>() {

    private val args: MohallaFragmentArgs by navArgs()
    private lateinit var mohallaAdapter: MohallaAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mfProgressbar.visible(false)
        val village = args.villageId
        val mainViewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)


        Log.i("home", args.companyName)

        if(isInternetConnection()){
            if(village == "None"){
                viewModel.getMohallas()

            }else{

                viewModel.getMohallas(village)
            }
        }else{
            if(village != "None"){
                mainViewModel.getMohallas(village)
                Thread.sleep(100)
            }
            Log.i("asdf", "no connection")

            mainViewModel.mohallas.observe(viewLifecycleOwner, Observer { list ->
                mohallaAdapter.mohallas = list
            })
        }

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