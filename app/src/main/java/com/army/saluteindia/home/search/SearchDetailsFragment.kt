package com.army.saluteindia.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.networklogin.searchResponses.Data
import com.army.saluteindia.data.repository.SearchRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.databinding.FragmentSearchDetailsBinding
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.handleApiError
import com.army.saluteindia.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class SearchDetailsFragment : BaseFragment<SearchViewModel, FragmentSearchDetailsBinding, SearchRepository>() {


    private val args: SearchDetailsFragmentArgs by navArgs()
    lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mfProgressbar.visible(false)
        var searchInfo = args.searchInfo
        var viewModel3 = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)

        if (searchInfo != null) {
            viewModel.getPersons(searchInfo)
        }
        addObserver()
        searchAdapter = SearchAdapter()

        binding.searchDetailsFragmentRecyclerView.adapter = searchAdapter
        binding.searchDetailsFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun getViewModel() = SearchViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchDetailsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): SearchRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return SearchRepository(api, userPreferences)
    }

    fun addObserver(){
        val dao = database.getInstance(requireContext()).dao

        viewModel._personsComplete.observe(viewLifecycleOwner, Observer {
            binding.mfProgressbar.visible(it is Resource.loading)
            when(it){
                is Resource.success -> {
                    val personList= mutableListOf<Data>()
                    it.value.data.forEach {
                        val person = it
                        personList.add(person)
                    }
                    searchAdapter.persons = personList
                }
                is Resource.failure -> handleApiError(it){

                }
            }
        })


    }


}