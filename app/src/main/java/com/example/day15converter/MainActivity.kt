package com.example.day15converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day15converter.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.buttonConvert.setOnClickListener { view ->
            if (binding.editText.text.isEmpty()) {
                Snackbar.make(view, "Enter cat`s length",
                    Snackbar.LENGTH_LONG).show()
            } else {
                val inputValue = binding.editText.text.toString().toFloat()

                if (binding.radioButtonMeter.isChecked) {
                    binding.editText.setText(convertParrotToMeter(inputValue).toString())
                } else {
                    binding.editText.setText(convertMeterToParrot(inputValue).toString())
                }
            }
        }
    }

    private fun convertParrotToMeter(parrot: Float): Float = (parrot / 7.6).toFloat()

    private fun convertMeterToParrot(meter: Float): Float = (meter * 7.6).toFloat()
}
