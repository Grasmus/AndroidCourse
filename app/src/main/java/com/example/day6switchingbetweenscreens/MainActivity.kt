package com.example.day6switchingbetweenscreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    companion object {
        const val RECIPIENT = "com.example.day6switchingbetweenscreens.RECIPIENT"
        const val MESSAGE = "com.example.day6switchingbetweenscreens.MESSAGE"
        const val SENDER = "com.example.day6switchingbetweenscreens.SENDER"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAbout : Button = findViewById(R.id.button_about)
        val buttonSecondActivity : Button = findViewById(R.id.button_second_activity)
        val editTextRecipient : EditText = findViewById(R.id.editText_recipient)
        val editTextMessage : EditText = findViewById(R.id.editText_message)
        val editTextSender : EditText = findViewById(R.id.editText_sender)

        buttonAbout.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }

        buttonSecondActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            intent.putExtra(RECIPIENT, editTextRecipient.text.toString())
            intent.putExtra(MESSAGE, editTextMessage.text.toString())
            intent.putExtra(SENDER, editTextSender.text.toString())

            startActivity(intent)
        }
    }
}
