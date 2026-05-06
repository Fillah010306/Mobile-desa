package com.example.fila_geometry.pertemuan6.ui

import android.content.Intent
import android.net.Uri
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

        // Setup Toolbar
        binding.toolbarWeb.setNavigationIcon(android.R.drawable.ic_menu_revert)
        binding.toolbarWeb.setNavigationOnClickListener {
            finish()
        }
        
        // Tombol untuk buka di Browser asli HP
        binding.btnOpenBrowser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

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