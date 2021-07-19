package com.army.saluteindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.*
import com.army.saluteindia.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = database.getInstance(this).dao


        viewModel.houses

        var btns = listOf(
            BTN(1, "78 BN", 3, 27, 150, 600, 2800)
        )

        var coys = listOf(
            COY( "A Coy", 5, 23, 150),
            COY( "B COY", 3, 10, 150),
            COY("C COY", 2, 3, 150)
        )

        var villages = listOf(
            VILLAGE( "SANT", 5, 12, 60, "A Coy"),
            VILLAGE( "BADLAPUR", 5, 14, 60, "A Coy"),
            VILLAGE( "MAHATAMA", 5, 11, 60, "A Coy"),
            VILLAGE( "RAMPUR", 5, 10, 60, "A Coy"),
            VILLAGE( "RAMGARH", 5, 7, 60, "A Coy"),
            VILLAGE( "AURANGABAD", 5, 15, 60, "B COY"),
            VILLAGE( "PHALTAN", 5, 15, 60, "B COY"),
            VILLAGE( "NANDED", 5, 15, 60, "B COY"),
            VILLAGE("NILGIRI", 5, 15, 60, "C COY"),
            VILLAGE( "AGADGAON", 5, 15, 60, "C COY"),
        )

        var mohallas = listOf(
            MOHALLA( "Digvijay", 3, 9, "SANT"),
            MOHALLA( "Swami", 3, 9, "SANT"),
            MOHALLA( "Samarth", 3, 9, "SANT"),
            MOHALLA( "Ganesh", 3, 9, "BADLAPUR"),
            MOHALLA( "Zebra", 3, 9, "BADLAPUR"),
            MOHALLA( "Red", 3, 9, "MAHATAMA"),
        )

        val houses = listOf(
            HOUSES(
                1,
                "Z-1",
                "SINGLE",
                "RED",
                "N",
                "Y",
                2,
                "28.679079",
                 "77.069710",
                1,
                3,
                4,
                5,
                2,
                1,
                4,
                1,
                "A Coy",
                "SANT",
                "Digvijay"
            ),
            HOUSES(
                2,
                "Z-2",
                "DOUBLE",
                "BLACK",
                "N",
                "Y",
                2,
                "30.679079",
                "79.069710",
                1,
                3,
                4,
                3,
                2,
                3,
                5,
                1,
                "A Coy",
                "SANT",
                "Digvijay"
            )

        )

        var person = listOf(
            PERSON(1, "Aprameya", 21, "9887554978"),
            PERSON(2, "Aviral", 17, "9887554978"),
            PERSON(3, "Alyssa", 35, "0882934847"),
            PERSON(4, "Amit", 50, "9852371363"),
            PERSON(5, "Jasleen", 34, "9852371363"),


            )

        lifecycleScope.launch {
            btns.forEach { dao.insertBtn(it) }
            coys.forEach { dao.insertCoy(it) }
            villages.forEach { dao.insertVillage(it) }
            mohallas.forEach { dao.insertMohalla(it) }
            houses.forEach { dao.insertHouse(it) }
            person.forEach { dao.insertPerson(it) }


        }
    }
}