package com.army.saluteindia.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.army.saluteindia.R
import com.army.saluteindia.databinding.FragmentLatLongBinding


class LatLongFragment : Fragment() {

    lateinit var binding: FragmentLatLongBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_lat_long, container, false
        )


        binding.mapButton.setOnClickListener { view: View ->
            val latitude = binding.latitudeEditText.text.toString().toFloat()
            val longitude = binding.longitudeEditText.text.toString().toFloat()

        }

        return binding.root

    }


}