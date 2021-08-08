package com.army.saluteindia.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.army.saluteindia.OverviewViewModel
import com.army.saluteindia.R
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.BTN
import com.army.saluteindia.data2.entities.PERSON
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val dao = database.getInstance(this).dao

        var btns = listOf(
            BTN(1, "78 BN", 3, 27, 150, 600, 2800)
        )

        var person = listOf(
            PERSON(1, "Aprameya", 21, "9887554978", "student"),
            PERSON(2, "Aviral", 17, "9887554978", "student"),
            PERSON(3, "Alyssa", 35, "0882934847", "Teacher"),
            PERSON(4, "Amit", 50, "9852371363", "Professor"),
            PERSON(5, "Jasleen", 34, "9852371363", "student"),
            )

        lifecycleScope.launch {
            btns.forEach { dao.insertBtn(it) }
            person.forEach { dao.insertPerson(it) }
        }
    }
}