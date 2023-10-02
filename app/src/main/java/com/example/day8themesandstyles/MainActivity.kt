package com.example.day8themesandstyles

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAbout : Button = findViewById(R.id.button_about)

        buttonAbout.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)

            startActivity(intent)
        }

        val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this)

        with (alertDialog)
        {
            setTitle("Cansel")
            setMessage("Cansel cancellation?")
            setPositiveButton("Cansel", null)
            setNegativeButton("Cansel", null)
            show()
        }
    }
}
