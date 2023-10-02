package com.example.day11menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var textView : TextView? = null
    val IDM_NEW : Int = 201
    val IDM_OPEN : Int = 202
    val IDM_SAVE : Int = 203
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        textView = findViewById(R.id.textView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val subMenu : SubMenu? = menu?.addSubMenu("File")

        subMenu?.setIcon(R.drawable.baseline_video_file_24)
        subMenu?.add(Menu.NONE, IDM_NEW, Menu.NONE, "New")
        subMenu?.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Open")
        subMenu?.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Save")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cat1 -> {
                textView!!.text = "You have chosen a male cat!"
            }
            R.id.action_cat2 -> {
                textView!!.text = "You have chosen a female cat!"
            }
            R.id.action_cat3 -> {
                textView!!.text = "You have chosen a kitten!"
            }
            IDM_NEW -> {
                textView!!.text = "New item selected"
            }
            IDM_OPEN -> {
                textView!!.text = "Open item selected"
            }
            IDM_SAVE -> {
                textView!!.text = "Save item selected"
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
