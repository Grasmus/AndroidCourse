package com.example.day28broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
       if (intent.action.equals(context.getString(R.string.boot_action), true)) {
           Toast.makeText(context, "Booted!", Toast.LENGTH_LONG).show()
       }
    }
}
