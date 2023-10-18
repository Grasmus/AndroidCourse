package com.example.day32alertdialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Important message!")
                .setMessage("How to feed a cat?")
                .setIcon(R.drawable.neco_arc)
                .setCancelable(true)
                .setPositiveButton("Delicious food") { _, _ ->
                    Toast.makeText(activity,
                        "You made a right decision",
                        Toast.LENGTH_LONG)
                        .show()
                }
                .setNeutralButton("Do nothing") { dialog, _ ->
                    dialog.cancel()
                }
                .setNegativeButton("Healthy food") { _, _ ->
                    Toast.makeText(activity,
                        "Maybe you are right",
                        Toast.LENGTH_LONG)
                        .show()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
