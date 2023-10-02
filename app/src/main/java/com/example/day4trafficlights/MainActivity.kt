package com.example.day4trafficlights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rootLayout : ConstraintLayout = findViewById(R.id.root_layout)
        val textViewTrafficLight : TextView = findViewById(R.id.textView_traffic_light)
        val buttonRed : Button = findViewById(R.id.button_red)
        val buttonYellow : Button = findViewById(R.id.button_yellow)
        val buttonGreen : Button = findViewById(R.id.button_green)

        buttonRed.setOnClickListener {
            textViewTrafficLight.text = getString(R.string.button_red_text)
            rootLayout.setBackgroundColor(getColor(R.color.red))
        }

        buttonYellow.setOnClickListener {
            textViewTrafficLight.text = getString(R.string.button_yellow_text)
            rootLayout.setBackgroundColor(getColor(R.color.yellow))
        }

        buttonGreen.setOnClickListener {
            textViewTrafficLight.text = getString(R.string.button_green_text)
            rootLayout.setBackgroundColor(getColor(R.color.green))
        }
    }
}
