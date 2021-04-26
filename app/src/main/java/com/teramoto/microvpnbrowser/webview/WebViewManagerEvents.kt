package com.teramoto.microvpnbrowser.webview

import android.content.Context

interface WebViewManagerEvents {
    fun onPreConfigure(context: Context) {}
    fun onPostConfigure(context: Context) {}
    fun onPreStartupLoadUrl(context: Context, url: String) {}
    fun onPostStartupLoadUrl(context: Context, url: String) {}
    fun onPreLoadUrl(context: Context, url: String) {}
    fun onPostLoadUrl(context: Context, url: String) {}
    fun onPreGoButton(context: Context, url: String) {}
    fun onPostGoButton(context: Context, url: String) {}
}