package com.army.saluteindia.home.addHouse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.databinding.FragmentAddHouseBinding
import com.army.saluteindia.network.NewHouse
import com.google.gson.Gson


class AddHouseFragment : Fragment() {

    lateinit var binding: FragmentAddHouseBinding

    var battalion = ""
    var coy = ""
    var village = ""
    var mohalla = ""
    var houseNo = ""
    var name = ""
    var relation = ""
    var sex = ""
    var age = ""
    var occupation = ""
    var mobileNo = ""
    var property = ""
    var floor = ""
    var colour = ""
    var rooms = ""
    var perimeterFence = ""
    var cowshed = ""
    var entryPoints = ""
    var latitude = ""
    var longitude = ""

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_add_house, container, false
        )

        binding.ahfAddHouseButton.setOnClickListener{
            getValues()
            var house = NewHouse(battalion, coy, village, mohalla, houseNo, name, relation, sex, age, occupation, mobileNo, property, floor, colour, rooms, perimeterFence, cowshed, entryPoints, latitude, longitude)
            /*lifecycleScope.launch {
                viewModel.createHouse(house)
            }*/
            var gson = Gson()
            var json = gson.toJson(house)
            Log.i("asdf", json)
        }
        return binding.root
    }

    private fun getValues() {
        battalion = binding.ahfBattalionMenu.editText?.text.toString()
        coy = binding.ahfCoy.editText?.text.toString()
        village = binding.ahfVillage.editText?.text.toString()
        mohalla = binding.ahfMohalla.editText?.text.toString()
        houseNo = binding.ahfHouseNo.editText?.text.toString()
        /*name = binding.ahfName.editText?.text.toString()
        relation = binding.ahfRelation.editText?.text.toString()
        sex = binding.ahfSex.editText?.text.toString()
        age = binding.ahfAge.editText?.text.toString()
        occupation = binding.ahfOccupation.editText?.text.toString()
        mobileNo = binding.ahfMobileNo.editText?.text.toString()*/
        property = binding.ahfProperty.editText?.text.toString()
        floor = binding.ahfFloor.editText?.text.toString()
        colour = binding.ahfColour.editText?.text.toString()
        rooms = binding.ahfNoOfRooms.editText?.text.toString()
        perimeterFence = binding.ahfPerimeterFence.editText?.text.toString()
        cowshed = binding.ahfCowshed.editText?.text.toString()
        entryPoints = binding.ahfEntryPoints.editText?.text.toString()
        latitude = binding.ahfLatitude.editText?.text.toString()
        longitude = binding.ahfLongitude.editText?.text.toString()
    }


}