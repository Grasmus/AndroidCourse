package com.example.day23catshandbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day23catshandbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataset = arrayOf(
            "00. Start",
            "01. What to feed a cat",
            "02. How to pet a cat",
            "03. How cat sleeps",
            "04. How to play with cat",
            "05. How to speak with cat",
            "06. Interesting facts about cat's life",
            "07. How to name a cat")
        val customAdapter = CustomAdapter(dataset) { data -> adapterOnClick(data) }

        customAdapter.itemCount

        binding.recyclerView.adapter = customAdapter
    }

    private fun adapterOnClick(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)

        intent.putExtra("title", position)

        startActivity(intent)
    }
}
