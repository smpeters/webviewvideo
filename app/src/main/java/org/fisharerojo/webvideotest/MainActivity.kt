package org.fisharerojo.webvideotest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myWebView = WebView(this)

        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowUniversalAccessFromFileURLs = true
        webSettings.mediaPlaybackRequiresUserGesture = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA)
            val permissions: MutableList<String> = ArrayList()
            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA)
            }
            if (permissions.isNotEmpty()) {
                requestPermissions(permissions.toTypedArray(), 111)
            }
        }

        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                    request.grant(request.resources)
            }
        }

        setContentView(myWebView)
        myWebView.loadUrl("https://webrtc.github.io/samples/src/content/devices/input-output/")
    }


}
