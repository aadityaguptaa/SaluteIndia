package com.army.saluteindia.home

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
import androidx.navigation.Navigation
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.databinding.ActivityMainBinding
import com.army.saluteindia.databinding.FragmentHomeBinding
import com.army.saluteindia.map.COY.CoyAdapter
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_home, container, false
        )


        binding.coy.setOnClickListener {view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_coyFragment)
        }

        binding.village.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToVillageFragment("home"))
        }

        binding.mohalla.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToMohallaFragment("home"))
        }


        return binding.root
    }


}