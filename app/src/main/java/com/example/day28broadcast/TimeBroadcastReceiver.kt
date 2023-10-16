package com.example.day28broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Date

class TimeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val message = StringBuilder("Current time is: ")
        val currentTime = Date()
        val formatter = SimpleDateFormat.getTimeInstance()
        message.append(formatter.format(currentTime))

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
