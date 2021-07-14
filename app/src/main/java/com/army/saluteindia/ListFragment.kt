package com.army.saluteindia

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomMasterTable
import com.army.saluteindia.data.Property
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.databinding.FragmentListBinding
import com.army.saluteindia.utils.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: PropertyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )

        viewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)






        return binding.root

    }



}