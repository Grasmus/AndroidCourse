package com.example.day13screen

import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button1)
        val buttonGetBrightness: Button = findViewById(R.id.button_set_brightness)
        val seekBar: SeekBar = findViewById(R.id.seekBar)
        val textView: TextView = findViewById(R.id.textView)

        val brightness = getBrightness()
        seekBar.progress = brightness

        textView.text = "Screen Brightness: $brightness"

        val canWrite = Settings.System.canWrite(this)

        if (!canWrite) {
            seekBar.isEnabled = false
            allowWritePermission()
        }

        button.setOnClickListener {
            displayScreenMetrics(textView)
        }

        buttonGetBrightness.setOnClickListener {
            getCurrentBrightness()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val brightness255 = ((progress / 100f) * 255).toInt()
                textView.text = "Screen Brightness : $brightness255"
                setBrightness(brightness255)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        getDeviceDensity()
    }

    fun setBrightness(value: Int) {
        Settings.System.putInt(
            this.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            value
        )
    }

    private fun allowWritePermission() {
        val intent = Intent(
            Settings.ACTION_MANAGE_WRITE_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
    }

    private fun getBrightness(): Int {
        return Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 0)
    }

    private fun oldMethodToGetDisplaySize() {
        val display: Display = windowManager.defaultDisplay
        val point = Point()

        display.getSize(point)

        val screenWidth: Int = point.x
        val screenHeight: Int = point.y

        val width = screenWidth.toString()
        val height = screenHeight.toString()

        val info = "Width: $width; Height: $height"

        Log.i("Screen", info)
    }

    private fun openScreenSettings() {
        val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun getDeviceDensity() {
        when (resources.displayMetrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> {
                Log.i("Density", "DENSITY_LOW")
            }
            DisplayMetrics.DENSITY_MEDIUM -> {
                Log.i("Density", "DENSITY_MEDIUM")
            }
            DisplayMetrics.DENSITY_HIGH -> {
                Log.i("Density", "DENSITY_HIGH")
            }
            DisplayMetrics.DENSITY_XHIGH -> {
                Log.i("Density", "DENSITY_XHIGH")
            }
            DisplayMetrics.DENSITY_XXHIGH -> {
                Log.i("Density", "DENSITY_XXHIGH")
            }
            DisplayMetrics.DENSITY_XXXHIGH -> {
                Log.i("Density", "DENSITY_XXXHIGH")
            }
            else -> Log.i("Density", resources.displayMetrics.densityDpi.toString())
        }
    }

    private fun getCurrentBrightness() {
        try {
            val curBrightnessValue = Settings.System.getInt(
                contentResolver,
                Settings.System.SCREEN_BRIGHTNESS
            )

            Log.i("Screen", "Current screen brightness: $curBrightnessValue")
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun displayScreenMetrics(textView: TextView) {
        var screen = ""
        var metrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= 30) {
            display?.apply {
                getRealMetrics(metrics)
                screen = """
                        Width: ${metrics.widthPixels} pixels
                        Height: ${metrics.heightPixels} pixels 
                        The Logical Density: ${metrics.density}  
                        X Dimension: ${metrics.xdpi} dot/inch
                        Y Dimension: ${metrics.ydpi} dot/inch
                        The screen density expressed as dots-per-inch: ${metrics.densityDpi}
                        A scaling factor for fonts displayed on the display: ${metrics.scaledDensity}
                    """
            }
        } else {
            // getMetrics() method was deprecated in api level 30
            windowManager.defaultDisplay.getMetrics(metrics)
            screen = """
                    Width: ${metrics.widthPixels} pixels
                    Height: ${metrics.heightPixels} pixels 
                    The Logical Density: ${metrics.density}  
                    X Dimension: ${metrics.xdpi} dot/inch
                    Y Dimension: ${metrics.ydpi} dot/inch
                    The screen density expressed as dots-per-inch: ${metrics.densityDpi}
                    A scaling factor for fonts displayed on the display: ${metrics.scaledDensity}"""
        }

        textView.text = screen
    }
}
