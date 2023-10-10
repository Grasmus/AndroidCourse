package com.example.day20keyboard

import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day20keyboard.databinding.FragmentEditNameDialogBinding
import com.google.android.material.snackbar.Snackbar

class EditNameDialog : DialogFragment(), OnEditorActionListener {
    private val binding: FragmentEditNameDialogBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.editText1.requestFocus()
        binding.editText4.inputType = InputType.TYPE_CLASS_PHONE
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        binding.editText1.setOnEditorActionListener(this)

        return binding.root
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (binding.editText1.text.toString() != "cat") {
                Snackbar.make(binding.root, "I will not find anything!", Snackbar.LENGTH_SHORT).show()
            }

            return true
        }

        return false
    }
}
