package com.example.day16whosaidmeow

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day16whosaidmeow.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private lateinit var soundPool: SoundPool
    private lateinit var assetManager: AssetManager

    private var catSound: Int = 0
    private var chickenSound: Int = 0
    private var cowSound: Int = 0
    private var dogSound: Int = 0
    private var duckSound: Int = 0
    private var sheepSound: Int = 0

    private var streamID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()

        assetManager = assets
        catSound = loadSound("neco-arc-sound-effect.ogg")
        chickenSound = loadSound("chicken.ogg")
        cowSound = loadSound("cow.ogg")
        dogSound = loadSound("inugami-korone-what-the-faq.ogg")
        duckSound = loadSound("mac-quack.ogg")
        sheepSound = loadSound("minecraft-sheep.ogg")

        binding.imageCat.setOnTouchListener { _, event ->  eventSoundHandler(event.action, catSound) }
        binding.imageChicken.setOnTouchListener { _, event ->  eventSoundHandler(event.action, chickenSound) }
        binding.imageCow.setOnTouchListener { _, event ->  eventSoundHandler(event.action, cowSound) }
        binding.imageDog.setOnTouchListener { _, event ->  eventSoundHandler(event.action, dogSound) }
        binding.imageDuck.setOnTouchListener { _, event ->  eventSoundHandler(event.action, duckSound) }
        binding.imageSheep.setOnTouchListener { _, event ->  eventSoundHandler(event.action, sheepSound) }
    }

    override fun onPause() {
        super.onPause()

        soundPool.release()
    }

    private fun playSound(sound: Int): Int {
        if (sound > 0) {
            streamID = soundPool.play(sound, 1F, 1F, 1, 0, 1F)
        }

        return streamID
    }

    private fun eventSoundHandler(eventAction: Int, soundId: Int): Boolean {
        return when (eventAction) {
            MotionEvent.ACTION_UP -> {
                if (streamID > 0)
                    soundPool.stop(streamID)
                true
            }
            MotionEvent.ACTION_DOWN -> {
                streamID = playSound(soundId)
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                soundPool.stop(streamID)
                true
            }
            else -> true
        }
    }

    private fun loadSound(fileName: String): Int {
        val afd: AssetFileDescriptor = try {
            application.assets.openFd(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("LoadSound", "Cannot load file: $fileName")

            return -1
        }

        return soundPool.load(afd, 1)
    }
}
