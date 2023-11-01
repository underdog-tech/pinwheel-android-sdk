package com.underdog_tech.pinwheel_android

import android.content.Context
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import java.io.File
import java.io.FileOutputStream

/**
 * This class is a javascript interface used for downloading blob data onto the user's device.
 * Currently its only application is for downloading direct deposit forms.
 */
class BlobDownloadJavaScriptInterface {
    var context: Context;

    constructor(context: Context) {
        this.context = context;
    }

    /**
     * Method to process Base64 data then save it locally.
     *
     * 1. Strip Base64 prefix from Base64 data
     * 2. Decode Base64 data
     * 3. Write Base64 data to file based on mime type located in prefix
     * 4. Save file locally
     */
    @android.webkit.JavascriptInterface
    fun processBase64Data(base64Data: String, blobUrl: String) {
        var fileName = "";
        var bytes = "";

        if (base64Data.startsWith("data:application/pdf;base64,")) {
            Log.i("JavascriptInterface", "Replacing bytes")
            // We need to get the component of the blob URL after the last '/'
            // Example: blob:http://10.0.2.2:5100/1cbe56fa-9924-419b-ac35-0cd44082bb85
            var blobUrlComponents = blobUrl.split("/")
            var fileId = blobUrlComponents[blobUrlComponents.size-1]
            fileName = "direct_deposit_form_${fileId}.pdf"
            bytes = base64Data.replaceFirst("data:application/pdf;base64,","")
        }

        if (fileName.isNotEmpty() && bytes.isNotEmpty()) {
            val downloadPath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName
            )

            val decodedString = Base64.decode(bytes, Base64.DEFAULT)
            val os = FileOutputStream(downloadPath, false)
            os.write(decodedString)
            os.flush()
        }
    }

    /**
     * Method to convert blobUrl to Blob, then process Base64 data on native side
     *
     * 1. Download Blob URL as Blob object
     * 2. Convert Blob object to Base64 data
     * 3. Pass Base64 data to Android layer for processing
     */
    fun getBase64StringFromBlobUrl(blobUrl: String): String {
        Log.i("JavascriptInterface", "Downloading $blobUrl ...")

        // Script to convert blob URL to Base64 data in Web layer, then process it in Android layer
        val script = "javascript: (() => {" +
                "async function getBase64StringFromBlobUrl() {" +
                "const xhr = new XMLHttpRequest();" +
                "xhr.open('GET', '${blobUrl}', true);" +
                "xhr.setRequestHeader('Content-type', 'application/pdf');" +
                "xhr.responseType = 'blob';" +
                "xhr.onload = () => {" +
                "if (xhr.status === 200) {" +
                "const blobResponse = xhr.response;" +
                "const fileReaderInstance = new FileReader();" +
                "fileReaderInstance.readAsDataURL(blobResponse);" +
                "fileReaderInstance.onloadend = () => {" +
                "console.log('Downloaded' + ' ' + '${blobUrl}' + ' ' + 'successfully!');" +
                "const base64data = fileReaderInstance.result;" +
                "BlobDownloadJavaScriptInterface.processBase64Data(base64data, '${blobUrl}');" +
                "}" + // file reader on load end
                "}" + // if
                "};" + // xhr on load
                "xhr.send();" +
                "}" + // async function
                "getBase64StringFromBlobUrl();" +
                "}) ()"

        return script
    }

    fun bind(webView: WebView) {
        webView.addJavascriptInterface(this, "BlobDownloadJavaScriptInterface")
    }
}