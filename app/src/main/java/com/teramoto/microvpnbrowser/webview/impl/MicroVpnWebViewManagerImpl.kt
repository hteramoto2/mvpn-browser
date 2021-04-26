package com.teramoto.microvpnbrowser.webview.impl

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import com.citrix.mvpn.api.MicroVPNSDK
import com.citrix.mvpn.api.ResponseStatusCode
import com.citrix.mvpn.exception.MvpnException
import com.citrix.mvpn.exception.NetworkTunnelNotStartedException


class MicroVpnWebViewManagerImpl(
    context: Context,
    webView: WebView,
    urlText: EditText,
    goButton: Button
) : BaseWebViewManager(context, webView, urlText, goButton) {

    private var tunnelStarted = false
    private var startupUrl: String = ""

    override fun onPreConfigure(context: Context) {
        Log.i(TAG, "onPreConfigure called for mVPN WebView.")
    }

    override fun onPostConfigure(context: Context) {
        Log.i(TAG, "onPostConfigure called for mVPN WebView.")
    }

    override fun onPreStartupLoadUrl(context: Context, url: String) {
        Log.i(TAG, "onPreStartupLoadUrl called for '$url'")
        if (!tunnelStarted) {
            MicroVPNSDK.startTunnel(context, Messenger(handler))
            startupUrl = url
        }
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

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (val statusCode = ResponseStatusCode.fromId(msg.what)) {
                ResponseStatusCode.START_TUNNEL_SUCCESS -> {
                    Log.i(TAG, "Tunnel started successfully!!!")
                    try {
                        val webViewClient = WebViewClient()
                        webView.webViewClient = webViewClient
                        MicroVPNSDK.enableWebViewObjectForNetworkTunnel(
                            context,
                            webView,
                            webViewClient
                        )
                        tunnelStarted = true
                    } catch (nse: NetworkTunnelNotStartedException) {
                        Log.e(TAG, nse.message!!)
                    } catch (e: MvpnException) {
                        Log.e(TAG, e.message!!)
                    }
                }
                ResponseStatusCode.START_TUNNEL_FAILED -> {
                    Log.e(TAG, "Failed to start tunnel!!!")
                    tunnelStarted = false
                }
                ResponseStatusCode.TUNNEL_ALREADY_RUNNING -> {
                    Log.i(TAG, "Tunnel is already running.")
                    tunnelStarted = true
                }
                ResponseStatusCode.SESSION_EXPIRED -> {
                    Log.i(TAG, "Session Expired!!!")
                    tunnelStarted = false
                }
                ResponseStatusCode.FOUND_LEGACY_MODE -> {
                    Log.e(TAG, "Cannot start tunnel for Legacy ManagementMode!!!")
                    tunnelStarted = false
                }
                ResponseStatusCode.FOUND_NON_WEBSSO_MODE -> {
                    Log.e(
                        TAG,
                        "Cannot start tunnel for NetworkAccess mode other than Tunneled - Web SSO!!!"
                    )
                    tunnelStarted = false
                }
                ResponseStatusCode.FOUND_NON_MANAGED_APP -> {
                    Log.e(
                        TAG, """Could not retrieve policies!!!
                            |This could be because of the following reasons:
                            |    1. SecureHub is not installed.
                            |    2. SecureHub enrollment is not completed.
                            |    3. App is not managed through CEM.
                            |""".trimMargin()
                    )
                    tunnelStarted = false
                }
                ResponseStatusCode.NO_NETWORK_CONNECTION -> {
                    Log.e(TAG, "Failed to start tunnel. No Network!!!")
                    tunnelStarted = false
                }
                else -> {
                    Log.e(TAG, "Unknown response status code. statusCode = $statusCode")
                    tunnelStarted = false
                }
            }
        }
    }

    override fun rawLoadUrl(url: String) {
        if (tunnelStarted) {
            super.rawLoadUrl(url)
        } else {
            Log.w(TAG, "Tunnel did not start. Load URL via direct connection.")
            super.rawLoadUrl(url)
        }
    }

    companion object {
        private const val TAG = "MicroVpnWebViewManager"
    }
}