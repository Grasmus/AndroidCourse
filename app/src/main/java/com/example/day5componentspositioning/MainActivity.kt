package com.example.day5componentspositioning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewSecond : TextView = findViewById(R.id.textView_second_replica)
        val textViewThird : TextView = findViewById(R.id.textView_third_replica)
        val textViewFourth : TextView = findViewById(R.id.textView_fourth_replica)
        val imageViewBottom : ImageView = findViewById(R.id.imageView_bottom_cat)

        imageViewBottom.setOnClickListener {
            val phrases = listOf(
                "Уже 6-а ранку, Наташ",
                "Вставай, ми там\nусе впустили",
                "Ми все впустили взагалі\nвсе, Наташ, чесно",
                "Наташ, ти чого знову лежиш?",
                "Час то йде",
                "Вставай давай\nми жерти хочемо")

            val shuffledList = phrases.shuffled()

            textViewSecond.text = shuffledList[0]
            textViewThird.text = shuffledList[1]
            textViewFourth.text = shuffledList[2]
        }
    }
}
