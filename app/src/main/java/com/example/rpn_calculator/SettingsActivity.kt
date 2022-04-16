package com.example.rpn_calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val applyButton = findViewById<Button>(R.id.applySettingsButton)
        applyButton.setOnClickListener {
            val data = Intent()
            val numberInput = findViewById<TextView>(R.id.decimalPlacesInput)
            if (numberInput.text.isNotBlank()) {
                data.putExtra(DECIMAL_PLACES_EXTRA_NAME, numberInput.text.toString().toInt())
            }
            setResult(Activity.RESULT_OK, data)
            finish()
        }

        val extras = intent.extras ?: return
        val numberInput = findViewById<TextView>(R.id.decimalPlacesInput)
        numberInput.text = extras.getInt(DECIMAL_PLACES_EXTRA_NAME).toString()
    }
}