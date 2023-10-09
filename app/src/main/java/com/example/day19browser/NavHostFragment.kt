package com.example.day19browser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day19browser.databinding.FragmentNavHostBinding

class NavHostFragment : Fragment(R.layout.fragment_nav_host) {
    private val binding: FragmentNavHostBinding by viewBinding(CreateMethod.INFLATE)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonWeb.setOnClickListener {buttonView ->
            val url = binding.editTextUrl.text.toString()
            val action = NavHostFragmentDirections.actionNavHostFragmentToWebViewFragment(url)

            buttonView.findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}
