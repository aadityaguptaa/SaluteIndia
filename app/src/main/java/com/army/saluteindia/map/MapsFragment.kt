package com.army.saluteindia.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.army.saluteindia.R
import com.army.saluteindia.data2.viewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment() {

    private val args: MapsFragmentArgs by navArgs()
    lateinit var viewModel: viewModel
    private var latitude = ""
    private var longitude = ""

    private val callback = OnMapReadyCallback { googleMap ->

        val houseList = viewModel.houses.value!!
        val listSize = (houseList.size) - 1
        var latSum = 0.0
        var longSum = 0.0

        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        for (i in 0..listSize) {
            val latLong = houseList[i]
            latSum += latLong.lat.toDouble()
            longSum += latLong.lon.toDouble()
            val sydney = LatLng(latLong.lat.toDouble(), latLong.lon.toDouble())
            if (args.houseId == null) {
                googleMap.addMarker(MarkerOptions().position(sydney).title(latLong.house))
            } else if (args.houseId == latLong.id) {
                latitude = latLong.lat
                longitude = latLong.lon
                googleMap.addMarker(MarkerOptions().position(sydney).title(latLong.house))
                break
            }

        }
        val ll: LatLng = if (args.houseId == null) {
            LatLng(latSum / (listSize + 1), longSum / (listSize + 1))
        } else {
            LatLng(latitude.toDouble(), longitude.toDouble())
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15F))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mohalla = args.mohallaId
        viewModel = ViewModelProvider(this).get(com.army.saluteindia.data2.viewModel::class.java)
        viewModel.getHouses(mohalla)

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        Thread.sleep(100)
        viewModel.houses.observe(viewLifecycleOwner, Observer {
            mapFragment?.getMapAsync(callback)
        })
    }
}