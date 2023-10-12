package com.example.day27notepad

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day27notepad.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val FILENAME = "notepad.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                openFile(FILENAME)
                true
            }
            R.id.action_save -> {
                saveFile(FILENAME)
                true
            }
            R.id.action_settings -> {
                Intent(this, SettingsActivity::class.java).also {
                    startActivity(it)
                }
                true
            }
            else -> true
        }
    }

    override fun onResume() {
        super.onResume()

        val prefs = PreferenceManager
            .getDefaultSharedPreferences(this)

        if (prefs.getBoolean(getString(R.string.pref_openmode), false)) {
            openFile(FILENAME)
        }

        val regular = prefs.getString(getString(R.string.pref_style), "")
        var typeface = Typeface.NORMAL
        val color = prefs.getString(getString(R.string.pref_color), "")

        if (regular!!.contains("Bold")) typeface += Typeface.BOLD
        if (regular.contains("Italic")) typeface += Typeface.ITALIC

        try {
            val fontSize = prefs.getString(getString(R.string.pref_size), "20")!!.toFloat()

            binding.editText.textSize = fontSize
            binding.editText.setTypeface(null, typeface)

            if (color !== "") {
                binding.editText.setTextColor(Color.parseColor(color))
            } else {
                binding.editText.setTextColor(getColor(R.color.black))
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private fun openFile(fileName: String) {
        val fileText =
            File(filesDir, fileName)
                .bufferedReader()
                .use { it.readText() }

        binding.editText.setText(fileText)
    }

    private fun saveFile(fileName: String) {
        openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(binding.editText.text.toString().toByteArray())
        }

        Snackbar.make(binding.root,
            "File is saved successfully",
            Snackbar.LENGTH_LONG)
            .show()
    }
}
