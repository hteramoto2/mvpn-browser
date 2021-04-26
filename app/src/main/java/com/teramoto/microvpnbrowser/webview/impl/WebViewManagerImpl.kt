package com.teramoto.microvpnbrowser.webview.impl

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText

class WebViewManagerImpl(
    context: Context,
    webView: WebView,
    urlText: EditText,
    goButton: Button
) : BaseWebViewManager(context, webView, urlText, goButton) {

    override fun onPreConfigure(context: Context) {
        Log.i(TAG, "onPreConfigure called for Plain WebView.")
    }

    override fun onPostConfigure(context: Context) {
        Log.i(TAG, "onPostConfigure called for Plain WebView.")
    }

    override fun onPreStartupLoadUrl(context: Context, url: String) {
        Log.i(TAG, "onPreStartupLoadUrl called for '$url'")
    }

    override fun onPostStartupLoadUrl(context: Context, url: String) {
        Log.i(TAG, "onPostStartupLoadUrl called for '$url'")
    }

    override fun onPreLoadUrl(context: Context, url: String) {
        Log.i(TAG, "onPreLoadUrl called for '$url'")
    }

    override fun onPostLoadUrl(context: Context, url: String) {
        Log.i(TAG, "onPostLoadUrl called for '$url'")
    }

    override fun onPreGoButton(context: Context, url: String) {
        Log.i(TAG, "onPreGoButton called for '$url'")
    }

    override fun onPostGoButton(context: Context, url: String) {
        Log.i(TAG, "onPostGoButton called for '$url'")
    }

    companion object {
        private const val TAG = "WebViewManagerImpl"
    }
}