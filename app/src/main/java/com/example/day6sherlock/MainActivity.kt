package com.example.day6sherlock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CHOOSE_THIEF = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonChoose : Button = findViewById(R.id.button_choose)

        buttonChoose.setOnClickListener {
            val questionIntent = Intent(this@MainActivity,
                ChooseThiefActivity::class.java)

            startActivityForResult(questionIntent, REQUEST_CHOOSE_THIEF)
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val textViewInto : TextView = findViewById(R.id.textview_into)

        if (requestCode == REQUEST_CHOOSE_THIEF) {
            if (resultCode == RESULT_OK) {
                val thiefName = data?.getStringExtra(THIEF)

                textViewInto.text = thiefName
            }
            else {
                textViewInto.text = ""
            }
        }
    }
}
