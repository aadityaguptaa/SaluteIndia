package com.army.saluteindia.map.COY

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.CoyRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.databinding.FragmentCoyBinding
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class CoyFragment : BaseFragment<CoyViewModel, FragmentCoyBinding, CoyRepository>() {

    lateinit var coyAdapter: CoyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cfProgressBar.visible(false)

        val dao = database.getInstance(requireContext()).dao

        coyAdapter = CoyAdapter()

        viewModel.getCoys()
        addObserver()

        val viewModel3 = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)

        binding.coyFragmentRecyclerView.adapter = coyAdapter
        binding.coyFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel3.coyList.observe(viewLifecycleOwner, Observer { list ->
            coyAdapter.setData(list)
        })

    }

    override fun getViewModel() = CoyViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCoyBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): CoyRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return CoyRepository(api, userPreferences)
    }

    private fun addObserver(){
        val dao = database.getInstance(requireContext()).dao

        viewModel._coysComplete.observe(viewLifecycleOwner, Observer {
            binding.cfProgressBar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    it.value.data.coy.forEach { companyItem ->
                        Log.i("coy", companyItem.toString())
                        val coy = COY(companyItem._id, companyItem.villagesCount, companyItem.mohallasCount, companyItem.housesCount)
                        lifecycleScope.launch {
                            dao.insertCoy(coy)
                        }
                    }
                    coyAdapter.setData(it.value.data.coy)
                }
                is Resource.failure -> handleApiError(it){
                    lifecycleScope.launch {
                        val companies = dao.getCoyList()
                        if(companies.value?.size != 0){
                            coyAdapter.setData(companies.value)
                        }else{
                            //@TODO make an error warning
                        }
                    }
                }
            }
        })


    }


}