package com.example.fila_geometry.pertemuan6.ui

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.fila_geometry.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // URL Default mengarah ke website Anda
        val url = intent.getStringExtra("url") ?: "http://fila-2sic.alwaysdata.net/"

        binding.webView.apply {
            webViewClient = WebViewClient()
            val webSettings: WebSettings = settings
            webSettings.javaScriptEnabled = true
            webSettings.domStorageEnabled = true // Penting untuk web modern
            loadUrl(url)
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}