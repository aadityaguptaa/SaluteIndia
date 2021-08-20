package com.army.saluteindia.home.addHouse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data.repository.AddHouseRepository
import com.army.saluteindia.data.repository.HouseRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.databinding.FragmentAddHouseBinding
import com.army.saluteindia.databinding.FragmentHousesBinding
import com.army.saluteindia.databinding.FragmentVillageBinding
import com.army.saluteindia.home.addHouse.jsonformat.*
import com.army.saluteindia.map.Village.VillageViewModel
import com.army.saluteindia.network.ApiService
import com.army.saluteindia.network.NewHouse
import com.army.saluteindia.ui.base.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import khttp.get
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


class AddHouseFragment : BaseFragment<AddHouseViewModel, FragmentAddHouseBinding, AddHouseRepository>() {

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
    var husbandName = ""
    var husbandAge = ""
    var husbandSex = ""
    var husbandOccupation = ""
    var husbandTel = ""
    var wifeName = ""
    var wifeAge = ""
    var wifeSex = ""
    var wifeOccupation = ""
    var wifeTel = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ahfAddHouseButton.setOnClickListener{
            if(isInternetConnection()){
                lifecycleScope.launch {
                    getValues()
                    val newHouse = createObjects()
                    viewModel.addHouse(newHouse)
                    MaterialAlertDialogBuilder(requireContext())
                        .setMessage("Successfully Added")
                        .setPositiveButton("Continue") { dialog, which ->
                            findNavController().navigate(AddHouseFragmentDirections.actionAddHouseFragmentToHomeFragment())
                        }
                        .show()
                }
            }else{
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("You are not connected to the internet")
                    .setPositiveButton("Try Again") { dialog, which ->
                        // Respond to positive button press
                    }
                    .show()
            }

        }
    }

    private fun getValues() {
        battalion = binding.ahfBattalionMenu.editText?.text.toString()
        coy = binding.ahfCoy.editText?.text.toString()
        village = binding.ahfVillage.editText?.text.toString()
        mohalla = binding.ahfMohalla.editText?.text.toString()
        houseNo = binding.ahfHouseNo.editText?.text.toString()
        property = binding.ahfProperty.editText?.text.toString()
        floor = binding.ahfFloor.editText?.text.toString()
        colour = binding.ahfColour.editText?.text.toString()
        rooms = binding.ahfNoOfRooms.editText?.text.toString()
        perimeterFence = binding.ahfPerimeterFence.editText?.text.toString()
        cowshed = binding.ahfCowshed.editText?.text.toString()
        entryPoints = binding.ahfEntryPoints.editText?.text.toString()
        latitude = binding.ahfLatitude.editText?.text.toString()
        longitude = binding.ahfLongitude.editText?.text.toString()

        husbandName = binding.husbandName.editText?.text.toString()
        husbandAge = binding.husbandAge.editText?.text.toString()
        husbandOccupation = binding.husbandOccupation.editText?.text.toString()
        husbandTel = binding.husbandNumber.editText?.text.toString()
        husbandSex = binding.husbandSex.editText?.text.toString()

        wifeName = binding.WifeName.editText?.text.toString()
        wifeAge = binding.wifeAge.editText?.text.toString()
        wifeOccupation = binding.wifeOccupation.editText?.text.toString()
        wifeTel = binding.wifeNumber.editText?.text.toString()
        wifeSex = "Female"
    }

    private fun createObjects(): newhousejson {
        val geo = Geo(latitude, longitude)
        val husband = Husband(husbandName, husbandAge, husbandSex, husbandOccupation, husbandTel)
        val wife = Wife(wifeName, wifeAge, wifeSex, wifeOccupation, wifeTel)
        val father = Father(wifeName, wifeAge, wifeSex, wifeOccupation, wifeTel)
        val mother = Mother(wifeName, wifeAge, wifeSex, wifeOccupation, wifeTel)
        val son = Son(wifeName, wifeAge, wifeSex, wifeOccupation, wifeTel)
        val daughter = Daughter(wifeName, wifeAge, wifeSex, wifeOccupation, wifeTel)
        val relatives = Relatives(daughter, father, husband, mother, son, wife)

        val house = newhousejson("", battalion, colour, cowshed.toBoolean(), coy, entryPoints, floor, geo, houseNo, mohalla, rooms, perimeterFence.toBoolean(), property, relatives, village)

        return house
    }

    override fun getViewModel() = AddHouseViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddHouseBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AddHouseRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ApiService::class.java, token)
        return AddHouseRepository(api, userPreferences)
    }

    fun isInternetConnection(): Boolean {
        var returnVal = false
        thread {
            returnVal = try {
                get("https://www.google.com/")
                true
            }catch (e:Exception){
                false
            }
        }.join()
        return returnVal
    }


}