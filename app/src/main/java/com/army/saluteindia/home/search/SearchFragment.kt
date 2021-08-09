package com.army.saluteindia.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.army.saluteindia.R
import com.army.saluteindia.data.networklogin.responses.searchInfo
import com.army.saluteindia.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    var village = ""
    var mohalla = ""
    var name = ""
    var fatherName = ""
    var houseNo = ""
    var mobileNumber = ""
    var occupation = ""
    var landArea = ""
    var floor = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.searchFragmentBattalionMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.sfSearchButton.setOnClickListener{
            getTextFields()
            val searchInfo = searchInfo("BTN A", village, mohalla, name, fatherName, houseNo, mobileNumber, occupation, landArea, floor)
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchDetailsFragment(searchInfo))
        }

        return binding.root
    }

    private fun getTextFields() {
        village = binding.sfVillage.editText?.text.toString()
        mohalla = binding.sfMohalla.editText?.text.toString()
        name = binding.sfName.editText?.text.toString()
        fatherName = binding.sfFatherName.editText?.text.toString()
        houseNo = binding.sfHouseNo.editText?.text.toString()
        mobileNumber = binding.sfMobileNo.editText?.text.toString()
        occupation = binding.sfOccupation.editText?.text.toString()
        landArea = binding.sfLandArea.editText?.text.toString()
        floor = binding.sfFloor.editText?.text.toString()
    }


}