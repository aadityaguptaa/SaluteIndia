package com.army.saluteindia.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.army.saluteindia.R
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.viewModel
import com.army.saluteindia.map.Mohalla.MohallaFragmentArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch


class MapsFragment : Fragment() {

/*
    private val args: MapsFragmentArgs by navArgs()
*/
    private val args: MapsFragmentArgs by navArgs()

    lateinit var viewModel: viewModel


    var latitude = 0.0
    var longitude = 0.0

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

/*

*/


        var houseList = viewModel.houses.value!!
        var listsize = (houseList.size) - 1

        var latSum = 0.0
        var longsum = 0.0

        val dao = database.getInstance(requireContext()).dao

        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        for(i in 0..listsize){
            var latlong = houseList[i]
            latSum += latlong.lat.toDouble()
            longsum += latlong.lon.toDouble()
            val sydney = LatLng(latlong.lat.toDouble(), latlong.lon.toDouble())
            googleMap.addMarker(MarkerOptions().position(sydney).title(latlong.house))

        }

        /*googleMap.setOnInfoWindowClickListener {
            var house = it.title

            lifecycleScope.launch {
                var houseProperty = dao.getHouseWithId(house)

                var village = houseProperty.village_id
                var mohalla = houseProperty.mohalla_id.toString()
                val houseNo = houseProperty.house
                var name = houseProperty.husband_id.toString()
                var fatherName = houseProperty.father_id.toString()
                val surname = "adi"
                var age = 27
                var mobileNumber = "9887554978"
                var occupation = "student"
                val landArea =houseProperty.property.toString()
                val houseType = houseProperty.perimeterFence
                val colour = houseProperty.colour
                val shed = houseProperty.cowshed
                val floor = houseProperty.floor

                var husband = dao.getPersonWithId(houseProperty.husband_id)
                name = husband.name
                Log.i("asdfg", name)
                age = husband.age
                mobileNumber = husband.tel
                var father = dao.getPersonWithId(houseProperty.father_id)
                fatherName = father.name
                mohalla = houseProperty.mohalla_id

                val house = mapsToDetails(village, mohalla, houseNo, name, fatherName, surname, age, mobileNumber, occupation, landArea, houseType, colour, shed, floor)
                findNavController().navigate(MapsFragmentDirections.actionMapsFragmentToDetailsFragment(house)) }

        }*/



        var ll = LatLng(latSum/(listsize+1), longsum/(listsize+1))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 5F))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mohalla = args.mohallaId

        viewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)
        viewModel.getHouses(mohalla)


        return inflater.inflate(R.layout.fragment_maps, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        Thread.sleep(100)
        viewModel.houses.observe(viewLifecycleOwner, Observer { list ->
            mapFragment?.getMapAsync(callback)
        })
    }
}