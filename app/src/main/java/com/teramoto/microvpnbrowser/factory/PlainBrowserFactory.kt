package com.teramoto.microvpnbrowser.factory

import android.content.Context
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import com.teramoto.microvpnbrowser.webview.WebViewManager
import com.teramoto.microvpnbrowser.webview.impl.WebViewManagerImpl

class PlainBrowserFactory : AbstractFactory {
    override fun createWebViewManager(
        context: Context,
        webView: WebView,
        urlText: EditText,
        goButton: Button
    ): WebViewManager = WebViewManagerImpl(context, webView, urlText, goButton)
}