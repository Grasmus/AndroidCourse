package com.example.day20keyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day20keyboard.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class FirstFragment : Fragment() {
    private val binding: FragmentFirstBinding by viewBinding(CreateMethod.INFLATE)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonShowDialog.setOnClickListener {
            val dialog = EditNameDialog()

            dialog.show(childFragmentManager, "tag")
        }

        binding.buttonGetLanguage.setOnClickListener {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            val ims = imm!!.currentInputMethodSubtype
            val localeString = ims!!.languageTag
            val locale = Locale(localeString)
            val currentLanguage = locale.displayLanguage

            Snackbar.make(binding.root, currentLanguage, Snackbar.LENGTH_SHORT).show()
        }

        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }
}
