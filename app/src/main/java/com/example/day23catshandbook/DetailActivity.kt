package com.example.day23catshandbook

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day23catshandbook.databinding.ActivityDetailBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding(CreateMethod.INFLATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = intent
        val resName = "n" + intent.getIntExtra("title", 0)
        val context: Context = baseContext

        val text: String = readRawTextFile(
            context, resources.getIdentifier(
                resName,
                "raw", "com.example.day23catshandbook"
            )
        )

        binding.webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null)
    }

    private fun readRawTextFile(context: Context, resId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resId)
        val inputReader = InputStreamReader(inputStream)
        val buffReader = BufferedReader(inputReader)
        var line: String?
        val builder = StringBuilder()
        try {
            while (buffReader.readLine().also { line = it } != null) {
                builder.append(line)
                builder.append("\n")
            }
        } catch (e: IOException) {
            return e.localizedMessage
        }
        return builder.toString()
    }
}
