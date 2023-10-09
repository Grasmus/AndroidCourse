package com.example.day19browser
import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (request!!.url.host!!.endsWith("youtube.com")) {
            return false
        }

        val intent = Intent(Intent.ACTION_VIEW, request!!.url)

        try {
            view!!.context.startActivity(intent)
        } catch (exception: Exception) {
            Log.v("WebClientIntent", exception.message.toString())
        }

        return true
    }
}
