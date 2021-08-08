package com.army.saluteindia.map.Mohalla

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


class MohallaFragment : BaseFragment<MohallaViewModel, FragmentMohallaBinding, MohallaRepository>() {

    private val args: MohallaFragmentArgs by navArgs()
    lateinit var mohallaAdapter: MohallaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mfProgressbar.visible(false)
        var village = args.villageId
        var viewModel3 = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)

        /*viewModel3.getMohallas(args.villageId)
        viewModel3.mohallas.observe(viewLifecycleOwner, Observer { list ->
            mohallaAdapter.mohallas = list
        })*/

        //@TODO make it work offline

        viewModel.getMohallas(village)
        addObserver()
        mohallaAdapter = MohallaAdapter()

        binding.mohallaFragmentRecyclerView.adapter = mohallaAdapter
        binding.mohallaFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

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

    fun addObserver(){
        val dao = database.getInstance(requireContext()).dao

        viewModel._mohallasComplete.observe(viewLifecycleOwner, Observer {
            binding.mfProgressbar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    val mohallaList= mutableListOf<MOHALLA>()
                    it.value.data.forEach {
                        val mohalla = MOHALLA(it._id, it.houseCount, it.houseCount, args.villageId)
                        mohallaList.add(mohalla)
                        lifecycleScope.launch {
                            dao.insertMohalla(mohalla)
                        }
                    }
                    mohallaAdapter.mohallas = mohallaList
                }
                is Resource.failure -> handleApiError(it){

                }
            }
        })


    }


}