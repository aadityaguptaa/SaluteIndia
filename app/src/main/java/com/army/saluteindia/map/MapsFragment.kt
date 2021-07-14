package com.army.saluteindia.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.army.saluteindia.R
import com.army.saluteindia.data.PropertyViewModel
import com.army.saluteindia.databinding.FragmentMohallaBinding
import com.army.saluteindia.map.Mohalla.MohallaFragmentArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment() {

/*
    private val args: MapsFragmentArgs by navArgs()
*/
    private val args: MapsFragmentArgs by navArgs()

    lateinit var viewModel: PropertyViewModel


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


        var latlongList = viewModel.latlongs.value!!
        Log.i("infofo", latlongList.toString())
        var listsize = (latlongList.size) - 1

        var latSum = 0.0
        var longsum = 0.0
        for(i in 0..listsize){
            var latlong = latlongList[i].split('/')
            latSum += latlong[0].toDouble()
            longsum += latlong[1].toDouble()

            val sydney = LatLng(latlong[0].toDouble(), latlong[1].toDouble())
            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        }

        /*var markerMumbai = MarkerOptions().position(sydney).title("Marker in Sydney")
        googleMap.addMarker(markerMumbai)
        googleMap.setOnMarkerClickListener(OnMarkerClickListener { marker ->
            Log.i("infofo", marker.title)
            //Using position get Value from arraylist
            false
        })*/
        var ll = LatLng(latSum/(listsize+1), longsum/(listsize+1))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 5F))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mohalla = args.mohallaName

        viewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)
        viewModel.getlatlongs(mohalla)


        return inflater.inflate(R.layout.fragment_maps, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        Thread.sleep(100)
        viewModel.latlongs.observe(viewLifecycleOwner, Observer { list ->
            mapFragment?.getMapAsync(callback)
        })
    }
}