package com.teramoto.microvpnbrowser.webview

interface WebViewManager {
    fun configure(): Boolean
    fun loadStartupUrl()
    fun loadUrl(url: String)
}