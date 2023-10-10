package com.example.day20keyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day20keyboard.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    companion object {
        private var back_pressed_time: Long = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (back_pressed_time + 2000 > System.currentTimeMillis()) {
            AlertDialog.Builder(this).apply {
                setTitle("Confirmation")
                setMessage("Are you sure, you want to leave this app?")

                setPositiveButton("Yes") {_, _ ->
                    super.onBackPressed()
                }

                setNegativeButton("No") {_, _ ->
                    Snackbar.make(binding.root, "Thank you", Snackbar.LENGTH_LONG).show()
                }

                setCancelable(true)
            }.create().show()
        } else {
            Snackbar.make(binding.root, "Press once again to exit!", Snackbar.LENGTH_SHORT).show()
        }

        back_pressed_time = System.currentTimeMillis()
    }

    override fun onUserLeaveHint() {
        Snackbar.make(binding.root, "Home button has been pressed", Snackbar.LENGTH_SHORT).show()

        super.onUserLeaveHint()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                onBackPressed()
                true
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
                event!!.startTracking()
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Snackbar.make(binding.root, "Volume button has been pressed", Snackbar.LENGTH_LONG)
                    .show()
                false
            }
            else ->
                super.onKeyDown(keyCode, event)
        }
    }
}
