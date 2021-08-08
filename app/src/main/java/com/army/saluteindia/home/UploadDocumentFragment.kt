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
import com.army.saluteindia.home.network.UploadApi
import com.army.saluteindia.home.network.UploadRequestBody
import com.army.saluteindia.home.network.response.UploadResponse
import com.army.saluteindia.utils.getFileName
import com.army.saluteindia.utils.snackbar
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class UploadDocumentFragment : Fragment(), UploadRequestBody.UploadCallback {

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

        binding.uploadButtonUploadFragment.setOnClickListener {
            uploadDoc()
        }

        return binding.root
    }

    private fun uploadDoc() {
        if(selectedDucument == null){
            binding.ufRootLayout.snackbar("Select a doc first")
            return
        }

        val parcelFileDescriptor = context?.contentResolver?.openFileDescriptor(selectedDucument!!, "r", null) ?: return
        val file = File(context?.cacheDir, context?.contentResolver?.getFileName(selectedDucument!!))
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)

        inputStream.copyTo(outputStream)

        binding.uploadFragmentProgressbar.progress = 0
        val body = UploadRequestBody(file, "document", this)

        UploadApi().uploadImage(
            MultipartBody.Part.createFormData("image", file.name, body),
            RequestBody.create(MediaType.parse("multipart/form-data"), " Document")
        ).enqueue(object : Callback<UploadResponse>{
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                binding.uploadFragmentProgressbar.progress = 100
                binding.ufRootLayout.snackbar(response.body()?.message.toString())
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                binding.ufRootLayout.snackbar(t.message!!)
            }

        })
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

    override fun onProgressUpdate(percentage: Int) {
        binding.uploadFragmentProgressbar.progress = percentage
    }
}