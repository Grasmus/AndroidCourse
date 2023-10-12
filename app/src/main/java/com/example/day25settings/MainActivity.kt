package com.example.day25settings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day25settings.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private lateinit var prefs: SharedPreferences

    private val APP_CROWS_COUNTER = "crowsCounter"

    private var crowCounter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonCrowCounter.setOnClickListener {
            binding.textViewCrowCount.printCrows(++crowCounter)
        }

        binding.buttonCrowCounterReset.setOnClickListener {
            crowCounter = 0
            binding.textViewCrowCount.printCrows(crowCounter)
        }

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    private fun TextView.printCrows(number: Int) {
        this.text = String.format(resources.getString(R.string.crow_counter_text), number)
    }

    override fun onPause() {
        super.onPause()

        val editor = prefs.edit()
        editor.putInt(APP_CROWS_COUNTER, crowCounter).apply()
    }

    override fun onResume() {
        super.onResume()

        if (prefs.contains(APP_CROWS_COUNTER)) {
            crowCounter = prefs.getInt(APP_CROWS_COUNTER, 0)
        }

        binding.textViewCrowCount.printCrows(crowCounter)
    }
}
