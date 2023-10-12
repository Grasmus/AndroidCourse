package com.example.day27notepad

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            else -> true
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
