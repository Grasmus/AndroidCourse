package com.example.day6switchingbetweenscreens

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.day6switchingbetweenscreens.MainActivity.Companion.MESSAGE
import com.example.day6switchingbetweenscreens.MainActivity.Companion.RECIPIENT
import com.example.day6switchingbetweenscreens.MainActivity.Companion.SENDER

class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textViewMain : TextView = findViewById(R.id.textView_main)
        val recipient : String? = intent.extras!!.getString(RECIPIENT)
        val message : String? = intent.extras!!.getString(MESSAGE)
        val sender : String? = intent.extras!!.getString(SENDER)

        if (recipient!!.isNotEmpty() && message!!.isNotEmpty() && sender!!.isNotEmpty()) {
            textViewMain.text =
                String.format(getString(R.string.textview_second_activity_message_template),
                    recipient,
                    message,
                    sender)
        }
        else {
            textViewMain.text = getString(R.string.textview_second_activity_message_error)
        }
    }
}
