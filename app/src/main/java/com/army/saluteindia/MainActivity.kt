package com.army.saluteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.ui.auth.AuthActivity
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.*
import com.army.saluteindia.home.HomeActivity
import com.army.saluteindia.utils.startNewActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })

    }
}