package com.army.saluteindia.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.army.saluteindia.R
import com.army.saluteindia.databinding.FragmentUploadDocumentBinding


class UploadDocumentFragment : Fragment() {

    lateinit var binding : FragmentUploadDocumentBinding

    private var selectedDucument: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_upload_document, container, false
        )

        binding.uploadFragmentExcelImage.setOnClickListener{
            openDocumentChooser()
        }

        return binding.root
    }

    private fun openDocumentChooser() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }
        startActivityForResult(intent, REQUEST_CODE_DOCUMENT_PICKER)
    }

    companion object {
        private const val REQUEST_CODE_DOCUMENT_PICKER = 100
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_DOCUMENT_PICKER -> {
                    selectedDucument = data?.data
                    Log.i("asdfg", selectedDucument?.path.toString())
                }
            }
        }
    }
}