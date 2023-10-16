package com.example.day28broadcast

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day28broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val timeBroadcastReceiver: BroadcastReceiver = TimeBroadcastReceiver()
    private val timeBroadcastIntentFilter = IntentFilter(Intent.ACTION_TIME_TICK)
    private val receiverFlags = ContextCompat.RECEIVER_EXPORTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        ContextCompat.registerReceiver(this, timeBroadcastReceiver, timeBroadcastIntentFilter, receiverFlags)
    }

    fun sendMessage(view: View) {
        Intent(getString(R.string.where_my_cat_action)).apply {
            putExtra("com.example.day28broadcast.broadcast.Message", getString(R.string.alarm_message))
            addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        }.also {
            sendBroadcast(it)
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(timeBroadcastReceiver)
    }
}
