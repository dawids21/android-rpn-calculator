package com.example.rpn_calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val DECIMAL_PLACES_EXTRA_NAME = "DecimalPlaces"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = "Settings"

        val extras = intent.extras ?: return
        val numberInput = findViewById<TextView>(R.id.decimalPlacesInput)
        numberInput.text = extras.getInt(DECIMAL_PLACES_EXTRA_NAME).toString()
    }
}