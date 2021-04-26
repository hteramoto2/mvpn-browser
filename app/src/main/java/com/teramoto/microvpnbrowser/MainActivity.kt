package com.teramoto.microvpnbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.teramoto.microvpnbrowser.factory.MicroVpnBrowserFactory
import com.teramoto.microvpnbrowser.factory.PlainBrowserFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isMvpn = true
        val factory = if (!isMvpn) PlainBrowserFactory() else MicroVpnBrowserFactory()
        val webViewManager = factory.createWebViewManager(
            this,
            findViewById(R.id.webview),
            findViewById(R.id.url_input),
            findViewById(R.id.go_button)
        )
        webViewManager.configure()
        webViewManager.loadStartupUrl()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}