package com.army.saluteindia.map.Details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
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
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

        val dao = database.getInstance(requireContext()).dao

        lifecycleScope.launch{
            val husbandName = dao.getPersonWithId(args.houseDetails.husband_id)
            val fatherName = dao.getPersonWithId(args.houseDetails.father_id)

            binding.villageNameDetailsFragment.setText(args.houseDetails.village_id)
            binding.mohallaNameDetailsFragment.setText(args.houseDetails.mohalla_id)
            binding.houseNumberDetailsFragment.text = args.houseDetails.house
            binding.headNameDetailsFragment.setText(husbandName.name)
            binding.fatherNameDetailsFragment.setText(fatherName.name)
            binding.ageDetailsFragment.text = args.houseDetails.age.toString()
            binding.mobileDetailsFragment.text = args.houseDetails.mobileNumber
            binding.occupationDetailsFragment.text = husbandName.occupation
            binding.landAreaDetailsFragment.text = args.houseDetails.property
            binding.colourDetailsFragment.text = args.houseDetails.colour
            binding.shedDetailsFragment.text = args.houseDetails.cowshed
            binding.floorDetailsFragment.text = args.houseDetails.floor
        }

        binding.mapImageDetailsFragment.setOnClickListener {
            Navigation.findNavController(it).navigate(DetailsFragmentDirections.actionDetailsFragmentToMapsFragment(args.houseDetails.mohalla_id))
        }

        binding.applyChangesButtonDetailsFragment.setOnClickListener{
            val newVillage = binding.villageNameDetailsFragment.text.toString()
            val newMohalla = binding.mohallaNameDetailsFragment.text.toString()
            val newName = binding.headNameDetailsFragment.text.toString()

            lifecycleScope.launch {

                try {
                    val village = dao.getVillageWithId(newVillage)
                    val mohalla = dao.getMohallaWithId(newMohalla)
                    val name = dao.getPersonWithName(newName)

                    val mohallaWithVillage = dao.getMohallaWithVillage(village, mohalla)

                    if(village != null && mohalla!=null && mohallaWithVillage!=null){
                        dao.updateVillageOfHouse(newVillage, args.houseDetails.house)
                        dao.updateMohallaOfHouse(newMohalla, args.houseDetails.house)
                        dao.updateheadInHouse(name.id, args.houseDetails.house)
                        val toast = Toast.makeText(
                            requireContext(),
                            "Update Successful",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }else{
                        val toast = Toast.makeText(
                            requireContext(),
                            "Please enter a valid Village/Mohalla",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }catch(e: Exception){
                    val toast = Toast.makeText(
                        requireContext(),
                        "Error Updating the Village/Mohalla Name",
                        Toast.LENGTH_SHORT
                    )

                    toast.show()
                }
            }
        }

        return binding.root
    }


}