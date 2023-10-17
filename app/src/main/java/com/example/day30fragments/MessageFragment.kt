package com.example.day30fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day30fragments.databinding.FragmentMessageBinding

class MessageFragment : Fragment(R.layout.fragment_message) {
    private val binding: FragmentMessageBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.messagefragmentTranslateButton.setOnClickListener { buttonView ->
            val message = binding.messagefragmentEdit.text.toString()
            val action = MessageFragmentDirections.actionMessageFragmentToConverterFragment(message)

            buttonView.findNavController().navigate(action)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment()
    }
}
