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
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.databinding.FragmentMohallaBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.map.Village.VillageAdapter
import com.army.saluteindia.map.Village.VillageFragmentArgs
import kotlinx.coroutines.launch


class MohallaFragment : Fragment() {


    private val args: MohallaFragmentArgs by navArgs()

    lateinit var binding : FragmentMohallaBinding
    lateinit var viewModel: viewModel
    private val viewModel2: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_mohalla, container, false
        )

        val village = args.villageId

        val dao = database.getInstance(requireContext()).dao


        viewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)
        if(village != "home") {
            viewModel.getMohallas(village)
        }

        val mohallaAdapter = MohallaAdapter()
        binding.mohallaFragmentRecyclerView.adapter = mohallaAdapter
        binding.mohallaFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel2.getMohallas(village)

        viewModel2._mohallas.observe(viewLifecycleOwner, Observer { list ->
            Log.i("asdfg", list.toString())
            val mohallaList= mutableListOf<MOHALLA>()
            list.forEach {
                val mohalla = MOHALLA(it._id, it.houseCount, it.houseCount, village)
                mohallaList.add(mohalla)
                lifecycleScope.launch {
                    dao.insertMohalla(mohalla)
                }
            }
            mohallaAdapter.setData(mohallaList)
        })

        //REPAIR THIS THREAD !!!!!!!!!!!!!!!!!!!!!!!!!!
        Thread.sleep(100)

        viewModel.mohallas.observe(viewLifecycleOwner, Observer { t ->
            mohallaAdapter.setData(t)
        })

        return binding.root
    }

}