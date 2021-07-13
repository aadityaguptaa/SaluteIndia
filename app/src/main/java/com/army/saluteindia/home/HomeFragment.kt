package com.army.saluteindia.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.army.saluteindia.R
import com.army.saluteindia.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_home, container, false
        )

        binding.googleMap.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_coyFragment)
        }

        return binding.root
    }


}