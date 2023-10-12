package com.example.day24camera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.day24camera.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var outputFileUri: Uri

    private val requestSetPhotoResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val thumbnailBitmap = result.data?.extras?.get("data") as Bitmap
                binding.imageViewPhoto.setImageBitmap(thumbnailBitmap)
            }
        }

    private val requestSavePhotoResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data !== null) {
                    performCrop()
                }
            }
        }

    private val requestPerformCropResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            if (result.resultCode == RESULT_OK) {
                val pic: Bitmap? = result.data?.extras?.getParcelable("data")
                if (pic !== null)
                    binding.imageViewPhoto.setImageBitmap(pic)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonOpenCamera.setOnClickListener {
            //getThumbnailPicture()
            saveFullImage()
        }
    }

    private fun getThumbnailPicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            try {
                requestSetPhotoResultLauncher.launch(it)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private fun saveFullImage() {
        val file = File(filesDir, "test.jpg")

        outputFileUri = FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
            flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        }.also {
            try {
                requestSavePhotoResultLauncher.launch(it)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private fun performCrop() {
        Intent("com.android.camera.action.CROP").apply {
            setDataAndType(outputFileUri, "image/*")
            putExtra("crop", true)
            putExtra("aspectX", 1)
            putExtra("aspectY", 1)
            putExtra("outputX", 256)
            putExtra("outputY", 256)
            putExtra("return-data", true)
        }.also {
            try {
                requestPerformCropResultLauncher.launch(it)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(binding.root,
                    "This device doesn't support image framing",
                    Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
