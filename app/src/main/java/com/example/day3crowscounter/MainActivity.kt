package com.example.day3crowscounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var counter : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSayHello : Button = findViewById(R.id.button_say_hello)
        val buttonCrowsCounter : Button = findViewById(R.id.button_crows_counter)
        val textViewMain : TextView = findViewById(R.id.textView_main)

        buttonSayHello.setOnClickListener {
            textViewMain.text = getString(R.string.say_hello_message)
        }

        buttonCrowsCounter.setOnClickListener {
            textViewMain.text = String.format(getString(R.string.counter_message), ++counter)
        }
    }
}
