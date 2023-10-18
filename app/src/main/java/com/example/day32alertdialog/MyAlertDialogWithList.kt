package com.example.day32alertdialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.DialogFragment

class MyAlertDialogWithList : DialogFragment() {
    private val catNames = arrayOf("Alfie", "Grady", "Neco Arc", "Olly")
    private val checkedItems = booleanArrayOf(true, false, true, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Choose a cat")
                    /*.setItems(catNames) {
                        _, which ->
                        Toast.makeText(activity, "Chosen cat: ${catNames[which]}",
                            Toast.LENGTH_SHORT).show()
                    }*/
                /*.setSingleChoiceItems(catNames, -1)
                { _, item ->
                    Toast.makeText(activity, "Favorite cat name: ${catNames[item]}",
                        Toast.LENGTH_SHORT).show()
                }*/
                .setMultiChoiceItems(catNames, checkedItems) {
                    _, which, isChecked ->
                    checkedItems[which] = isChecked
                }
                .setPositiveButton("Done") { _, _ ->
                    for (i in catNames.indices) {
                        val checked = checkedItems[i]
                        if (checked) {
                            println(catNames[i])
                        }
                    }
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }

            val dialog = builder.create()

            object : CountDownTimer(10000, 1000) {
                override fun onTick(l: Long) {}

                override fun onFinish() {
                    dialog.cancel()
                }
            }.start()

            dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
