package com.army.saluteindia.home.search.personDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.army.saluteindia.R
import com.army.saluteindia.databinding.FragmentSearchPersonDetailsBinding
import com.army.saluteindia.map.Details.DetailsFragmentDirections
import com.army.saluteindia.map.House.HousesFragmentArgs


class SearchPersonDetailsFragment : Fragment() {

    private val args: SearchPersonDetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentSearchPersonDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_person_details, container, false)
        binding.headNamePersonDetailsFragment.setText(args.personDetails.name)
        binding.agePersonDetailsFragment.setText(args.personDetails.age)
        binding.mobilePersonDetailsFragment.text = args.personDetails.tel
        binding.occupationPersonDetailsFragment.text = args.personDetails.occupation
        binding.villageNamePersonDetailsFragment.setText(args.personDetails.house.village)
        binding.mohallaNamePersonDetailsFragment.setText(args.personDetails.house.mohalla)
        binding.houseNumberPersonDetailsFragment.setText(args.personDetails.house.house)
        binding.villageNamePersonDetailsFragment.setText(args.personDetails.house.village)
        binding.fatherNamePersonDetailsFragment.setText(args.personDetails.house.father)
        binding.landAreaPersonDetailsFragment.text = args.personDetails.house.property
        binding.colourPersonDetailsFragment.text = args.personDetails.house.colour
        binding.shedPersonDetailsFragment.text = args.personDetails.house.cowshed.toString()
        binding.floorPersonDetailsFragment.text = args.personDetails.house.floor

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    findNavController().navigate(SearchPersonDetailsFragmentDirections.actionSearchPersonDetailsFragmentToMapsFragment(args.personDetails.house.mohalla!!))
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }



        return binding.root
    }


}