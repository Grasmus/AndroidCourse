package com.example.day2hellokitty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButtonKitty : ImageButton = findViewById(R.id.imageButton_kitty)
        val textViewCatsName : TextView = findViewById(R.id.textView_cats_name)
        val editTextCatsName : EditText = findViewById(R.id.editText_cats_name)

        imageButtonKitty.setOnClickListener {
            if (editTextCatsName.text.isEmpty()) {
                textViewCatsName.text = getString(R.string.kitty_name)
            } else {
                val message = getString(R.string.text_view_greetings) + editTextCatsName.text.toString()
                textViewCatsName.text = message
            }
        }
    }
}
