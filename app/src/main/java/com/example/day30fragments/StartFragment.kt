package com.example.day30fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day30fragments.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start) {
    private val binding: FragmentStartBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBegin.setOnClickListener { buttonView ->
            val action = StartFragmentDirections.actionStartFragmentToMessageFragment()

            buttonView.findNavController().navigate(action)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }
}
