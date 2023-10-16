package com.example.day28broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Message has been detected: "
                + intent.getStringExtra("com.example.day28broadcast.broadcast.Message"), Toast.LENGTH_LONG)
            .show()
    }
}
