package com.teramoto.microvpnbrowser.webview.impl

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.teramoto.microvpnbrowser.consts.DEFAULT_WEBPAGE
import com.teramoto.microvpnbrowser.webview.WebViewManager
import com.teramoto.microvpnbrowser.webview.WebViewManagerEvents

abstract class BaseWebViewManager(
    private val context: Context,
    private val webView: WebView,
    private val urlText: EditText,
    private val goButton: Button
) : WebViewManager, WebViewManagerEvents {

    @SuppressLint("SetJavaScriptEnabled")
    override fun configure(): Boolean {
        onPreConfigure(context)

        webView.settings.javaScriptEnabled = true
        webView.settings.supportMultipleWindows()
        webView.webViewClient = WebViewClient()

        goButton.setOnClickListener {
            goButtonHandler()
        }

        urlText.setOnEditorActionListener { _, actionId, event ->
            Log.d(TAG, "UrlText actionId = $actionId event = $event")
            if (actionId == EditorInfo.IME_ACTION_NEXT ||
                (actionId == EditorInfo.IME_ACTION_UNSPECIFIED &&
                        event.keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.action == KeyEvent.ACTION_DOWN)
            ) {
                goButtonHandler()
            }
            false
        }

        onPostConfigure(context)
        return true
    }

    override fun loadStartupUrl() {
        var address = urlText.text?.toString() ?: ""
        if (address.isEmpty()) {
            urlText.setText(DEFAULT_WEBPAGE, TextView.BufferType.EDITABLE)
            address = DEFAULT_WEBPAGE
        }

        onPreStartupLoadUrl(context, address)
        rawLoadUrl(address)
        onPostStartupLoadUrl(context, address)
    }

    override fun loadUrl(url: String) {
        onPreLoadUrl(context, url)
        rawLoadUrl(url)
        onPostLoadUrl(context, url)
    }

    protected open fun rawLoadUrl(url: String) {
        webView.loadUrl(url)
    }

    private fun goButtonHandler() {
        val navAddress = urlText.text?.toString() ?: ""

        onPreGoButton(context, navAddress)
        rawLoadUrl(navAddress)
        onPostGoButton(context, navAddress)
    }

    companion object {
        private const val TAG = "BaseWebViewManager"
    }
}