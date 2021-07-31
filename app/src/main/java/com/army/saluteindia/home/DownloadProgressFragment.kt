package com.army.saluteindia.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data2.database
import kotlinx.coroutines.launch


class DownloadProgressFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val dao = database.getInstance(requireContext()).dao

        lifecycleScope.launch {
            viewModel.getHouses()
            viewModel.getCompleteVillageList()
            viewModel.getCompleteMohallaList()
            viewModel.getCompleteHousesList()
            findNavController().navigate(DownloadProgressFragmentDirections.actionDownloadProgressFragmentToHomeFragment())
        }
        return inflater.inflate(R.layout.fragment_download_progress, container, false)
    }


}