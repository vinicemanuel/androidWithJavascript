package com.if1001.cin.webviewtestandroid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.web_view!!.settings.javaScriptEnabled = true
        this.web_view!!.addJavascriptInterface(MainActivity.WebViewJavaScriptInterface(), "android_app")
        this.web_view!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        this.button.setOnClickListener {
            Log.d("button_pressed","apertando changeNamebotão")
            val string = "valor de teste"
            this.web_view.loadUrl("javascript:('$string')")
        }

//        this.web_view.loadUrl("https://google.com")
        this.web_view.loadUrl("file:///android_asset/example.html")
    }

    class WebViewJavaScriptInterface(){
        @JavascriptInterface
        fun androidLogDebug(text: String){
            Log.d("javascript_interface", text)
        }
    }

}
