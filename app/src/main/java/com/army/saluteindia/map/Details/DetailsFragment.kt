package com.army.saluteindia.map.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.army.saluteindia.R
import com.army.saluteindia.data2.database
import com.army.saluteindia.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

        val dao = database.getInstance(requireContext()).dao


        binding.villageNameDetailsFragment.setText(args.houseDetails.village)
        binding.mohallaNameDetailsFragment.setText(args.houseDetails.mohalla)
        binding.houseNumberDetailsFragment.text = args.houseDetails.houseNo
        binding.headNameDetailsFragment.text = args.houseDetails.name
        binding.fatherNameDetailsFragment.text = args.houseDetails.fatherName
        binding.ageDetailsFragment.text = args.houseDetails.age.toString()
        binding.mobileDetailsFragment.text = args.houseDetails.mobileNumber
        binding.occupationDetailsFragment.text = args.houseDetails.occupation
        binding.landAreaDetailsFragment.text = args.houseDetails.landArea
        binding.colourDetailsFragment.text = args.houseDetails.colour
        binding.shedDetailsFragment.text = args.houseDetails.shed
        binding.floorDetailsFragment.text = args.houseDetails.floor

        binding.applyChangesButtonDetailsFragment.setOnClickListener{
            var newVillage = binding.villageNameDetailsFragment.text.toString()

            lifecycleScope.launch {
                var x = dao.getVillageWithId(newVillage)
                if(x != null){
                    dao.updateVillageOfHouse(newVillage, args.houseDetails.houseNo)
                }
            }
        }

        return binding.root
    }


}