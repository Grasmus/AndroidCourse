package com.example.day30fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day30fragments.databinding.FragmentConverterBinding

class ConverterFragment : Fragment(R.layout.fragment_converter) {
    private val binding: FragmentConverterBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val message = ConverterFragmentArgs.fromBundle(requireArguments()).message
        var translation = ""

        repeat (message.length) {
            translation += "nya "
        }

        binding.converterfragmentText.text = translation

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConverterFragment()
    }
}
