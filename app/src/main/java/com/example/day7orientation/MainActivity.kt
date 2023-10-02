package com.example.day7orientation

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Surface
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOne : Button = findViewById(R.id.button_one)
        val buttonTwo : Button = findViewById(R.id.button_two)
        val textViewOrientation : TextView = findViewById(R.id.textView_orientation)

        buttonOne.setOnClickListener {
            textViewOrientation.text = getScreenOrientation()
        }

        buttonTwo.setOnClickListener {
            textViewOrientation.text = getRotateOrientation()
        }
    }

    private fun getScreenOrientation(): String {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> "Portrait orientation"
            Configuration.ORIENTATION_LANDSCAPE -> "Landscape orientation"
            else -> ""
        }
    }

    private fun getRotateOrientation(): String {
        return when (windowManager.defaultDisplay.rotation) {
            Surface.ROTATION_0 -> "Did not rotate"
            Surface.ROTATION_90 -> "Turned 90 degrees clockwise"
            Surface.ROTATION_180 -> "Turned 180 degrees"
            Surface.ROTATION_270 -> "Turned 90 degrees counterclockwise"
            else -> "Idk"
        }
    }
}
