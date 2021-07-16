package com.army.saluteindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = database.getInstance(this).dao

        var btns = listOf(
            BTN(1, "78 BN", 3, 27, 150, 600, 2800)
        )

        var coys = listOf(
            COY(1, "A Coy", 5, 23, 150, 600, 1),
            COY(2, "B Coy", 3, 10, 150, 600, 1),
            COY(3, "C Coy", 2, 3, 150, 600, 1)
        )

        var villages = listOf(
            VILLAGE(1, "SANT", 5, 12, 60, 1),
            VILLAGE(2, "BADLAPUR", 5, 14, 60, 1),
            VILLAGE(3, "MAHATAMA", 5, 11, 60, 1),
            VILLAGE(4, "RAMPUR", 5, 10, 60, 1),
            VILLAGE(5, "RAMGARH", 5, 7, 60, 1),
            VILLAGE(6, "AURANGABAD", 5, 15, 60, 2),
            VILLAGE(7, "PHALTAN", 5, 15, 60, 2),
            VILLAGE(8, "NANDED", 5, 15, 60, 2),
            VILLAGE(9, "NILGIRI", 5, 15, 60, 3),
            VILLAGE(10, "AGADGAON", 5, 15, 60, 3),
        )

        var mohallas = listOf(
            MOHALLA(1, "Digvijay", 3, 9, 1),
            MOHALLA(2, "Swami", 3, 9, 1),
            MOHALLA(3, "Samarth", 3, 9, 1),
            MOHALLA(4, "Ganesh", 3, 9, 2),
            MOHALLA(5, "Zebra", 3, 9, 2),
            MOHALLA(6, "Red", 3, 9, 3),
        )

        var houses = listOf(
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
                3,
                1,
                3,
                4,
                1,
                1,
                1,
                1
            ),
            HOUSES(
                2,
                "Z-2",
                "SINGLE",
                "RED",
                "N",
                "Y",
                2,
                "30.679079",
                "79.069710",
                1,
                3,
                4,
                3,
                1,
                3,
                4,
                1,
                1,
                2,
                1
            )

        )

        var person = listOf(
            PERSON(1, "Aditya", 21, "9887554978"),
            PERSON(2, "Tanya", 17, "9887554978"),
            PERSON(3, "Rekha", 35, "0882934847"),
            PERSON(4, "Pramod", 50, "9852371363"),

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