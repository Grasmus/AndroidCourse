package com.example.day18animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day18animations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sunRiseAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise)
        val clockTurnAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.clock_turn)
        val hourHandAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.hour_turn)

        binding.sun.startAnimation(sunRiseAnimation)
        binding.clock.startAnimation(clockTurnAnimation)
        binding.hourHand.startAnimation(hourHandAnimation)
    }
}
