package com.example.day12popupmenu

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)
        val textView : TextView = findViewById(R.id.textView)
        val imageView : ImageView = findViewById(R.id.imageView)

        button.setOnClickListener{
            showPopUpMenu(it)
        }

        textView.setOnClickListener{
            showPopUpMenu(it)
        }

        imageView.setOnClickListener{
            showPopUpMenu(it)
        }
    }

    private fun showPopUpMenu(v: View) {
        val popupMenu = PopupMenu(this, v)
        popupMenu.inflate(R.menu.popupmenu)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.menu1 -> {
                    Snackbar.make(v, "You have chosen popupmenu 1", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.menu2 -> {
                    Snackbar.make(v, "You have chosen popupmenu 2", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.menu3 -> {
                    Snackbar.make(v, "You have chosen popupmenu 3", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.green -> {
                    Snackbar.make(v, "You have chosen green color", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.yellow -> {
                    Snackbar.make(v, "You have chosen yellow color", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.red -> {
                    Snackbar.make(v, "You have chosen red color", Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }

        popupMenu.setOnDismissListener {
            Snackbar.make(v, "On dismiss", Snackbar.LENGTH_LONG).show()
        }

        popupMenu.show()
    }
}
