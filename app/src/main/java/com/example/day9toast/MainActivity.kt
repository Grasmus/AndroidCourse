package com.example.day9toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToast : Button = findViewById(R.id.button_toast)
        val buttonToast2 : Button = findViewById(R.id.button_toast2)

        buttonToast.setOnClickListener {
            val inflater = layoutInflater
            val container = findViewById<ViewGroup>(R.id.custom_toast_container)

            if (container != null) {
                val layout : View = inflater.inflate(R.layout.custom_toast, container)
                val text : TextView = findViewById(R.id.text)
                text.text = getString(R.string.toast_text)

                with(Toast(applicationContext))
                {
                    setGravity(Gravity.CENTER, 0, 0) //doesn't work on newer android versions starting from android 11 :/
                    duration = Toast.LENGTH_LONG
                    view = layout //works only with API level 29 or lower
                    show()
                }
            }
        }

        buttonToast2.setOnClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_text, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0) //doesn't work on newer android versions starting from android 11 :/

            val toastContainer = toast?.view as LinearLayout? //works only with API level 29 or lower

            if (toastContainer != null) {
                val catImage = ImageView(this)

                catImage.setImageResource(R.drawable.hungrycat)
                toastContainer.addView(catImage, 0)
            }

            toast.addCallback(object: Toast.Callback() {
                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("Toast", "Shown")
                }

                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("Toast", "Hiden")
                }
            })

            toast.show()
        }
    }
}
