package com.army.saluteindia.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import androidx.fragment.app.Fragment
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.ui.auth.LoginFragment
import com.army.saluteindia.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

fun <A: Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also{
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}


fun View.visible(isVisible: Boolean){
    visibility = if(isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
    alpha = if(enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null ){
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
}

fun Fragment.handleApiError(
    failure: Resource.failure,
    retry: (() -> Unit)? = null
){
    when{
        failure.isNetworking -> requireView().snackbar("Please check your internet connection", retry)
        failure.errorCode == 401 -> {
            if(this is LoginFragment){
                requireView().snackbar("You've entered incorrect email or password")
            }else{
                (this as BaseFragment<*, *, *>).logout()
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}

fun ContentResolver.getFileName(uri: Uri): String{
    var name = ""
    val cursor = query(uri, null, null, null, null)
    cursor?.use{
        it.moveToFirst()
        name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}
