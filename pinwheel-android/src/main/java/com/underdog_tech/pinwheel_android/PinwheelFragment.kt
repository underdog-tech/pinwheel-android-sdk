package com.underdog_tech.pinwheel_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment

class PinwheelFragment : Fragment() {

    var pinwheelEventListener: PinwheelEventListener? = null

    companion object {
        fun newInstance(linkToken: String): PinwheelFragment {
            val args = Bundle()
            args.putString("linkToken", linkToken)
            val fragment = PinwheelFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.pinwheel_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = view.findViewById<WebView>(R.id.webView)

        Pinwheel.init(webView, readLinkToken(), pinwheelEventListener)
    }

    private fun readLinkToken() : String {
        return arguments?.getString(("linkToken")) ?: throw IllegalStateException("In order to proceed, you need to provide the Link Token")
    }
}