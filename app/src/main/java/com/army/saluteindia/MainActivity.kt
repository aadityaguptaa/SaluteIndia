package com.army.saluteindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.army.saluteindia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var binding:ActivityMainBinding
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}