package com.example.day32alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day32alertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonShowAlertDialog.setOnClickListener {
            val dialog = MyDialogFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            dialog.show(transaction, "nyDialog")
        }

        binding.buttonShowAlertListDialog.setOnClickListener {
            val dialog = MyAlertDialogWithList()

            dialog.show(supportFragmentManager, "myDialog")
        }

        binding.buttonShowRatingDialog.setOnClickListener {
            showRatingDialog()
        }
    }

    private fun showRatingDialog() {

        val linearLayout = layoutInflater.inflate(R.layout.ratingdialog, null)

        val rating = linearLayout.findViewById<RatingBar>(R.id.ratingbar)

        AlertDialog.Builder(this).apply {
            setIcon(R.drawable.baseline_star_24)
            setTitle("Rate a favorite cat!")
            setView(linearLayout)
            setPositiveButton("Done") { dialog, _ ->
                binding.textViewRating.text = rating.rating.toString()
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            create()
            show()
        }
    }
}
