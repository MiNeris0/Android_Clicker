package com.example.rina

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref :SharedPreferences
    private val keyForNumber = "number_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("myAppFile", MODE_PRIVATE)
        val number = findViewById<TextView>(R.id.number)
        val buttonUp = findViewById<Button>(R.id.buttonUp)
        val buttonDown = findViewById<Button>(R.id.buttonDown)
        var counter = getNumber()
        number.text = counter.toString()
        buttonUp.text = "Up!"
        buttonDown.text = "Down!"

        buttonUp.setOnClickListener {
            Log.d("MyLogTag", "Button up click.")
            number.text = (++counter).toString()
            saveNumber(counter)
        }

        buttonDown.setOnClickListener {
            Log.d("MyLogTag", "Button down click.")
            number.text = (--counter).toString()
            saveNumber(counter)
        }
    }

    private fun saveNumber(number: Int){
        val editor = sharedPref.edit()
        editor.putInt(keyForNumber, number)
        editor.commit()
    }

    private fun getNumber(): Int {
        return sharedPref.getInt(keyForNumber, 0)
    }
}