package com.army.saluteindia.map.COY

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.army.saluteindia.ListAdapter
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.databinding.FragmentCoyBinding
import kotlinx.coroutines.launch


class CoyFragment : Fragment() {

    lateinit var binding : FragmentCoyBinding

    lateinit var viewModel: viewModel


    private val viewModel2: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_coy, container, false
        )
        val dao = database.getInstance(requireContext()).dao



        viewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)

        var coyAdapter = CoyAdapter()
        binding.coyFragmentRecyclerView.adapter = coyAdapter
        binding.coyFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel2._houses.observe(viewLifecycleOwner, Observer {
            Log.i("asdfg", it.toString())
            it.forEach {
                lifecycleScope.launch {
                    dao.insertCoy(it)
                }
            }
            coyAdapter.setData(it)
        })


        viewModel.coyList.observe(viewLifecycleOwner, Observer { list ->
            coyAdapter.setData(list)
        })
        return binding.root
    }


}