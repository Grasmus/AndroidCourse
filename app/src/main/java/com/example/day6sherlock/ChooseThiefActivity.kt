package com.example.day6sherlock

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup

const val THIEF = "com.example.day6sherlock.THIEF"
class ChooseThiefActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_thief)

        val radioGroup : RadioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { _, optionId ->
            val answerIntent = Intent()

            when (optionId) {
                R.id.radio_cat -> answerIntent.putExtra(THIEF, "It was a Cat!")
                R.id.radio_dog -> answerIntent.putExtra(THIEF, "It was a Dog!")
                R.id.radio_crow -> answerIntent.putExtra(THIEF, "It was a Crow!")
            }

            setResult(RESULT_OK, answerIntent)
            finish()
        }
    }
}
