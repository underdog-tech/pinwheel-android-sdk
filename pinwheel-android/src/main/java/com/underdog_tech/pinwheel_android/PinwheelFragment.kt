package com.underdog_tech.pinwheel_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class PinwheelFragment : Fragment() {

    var pinwheelEventListener: PinwheelEventListener? = null
    var webView: WebView? = null

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
        enableFullscreenMode()
        return inflater.inflate(R.layout.pinwheel_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webView)
        webView?.let {
            attachOnBackPressedCallback()
            Pinwheel.init(it, readLinkToken(), pinwheelEventListener)
        }
    }

    private fun attachOnBackPressedCallback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView?.canGoBack() == true) {
                    webView?.goBack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun readLinkToken(): String {
        return arguments?.getString(("linkToken")) ?: throw IllegalStateException("In order to proceed, you need to provide the Link Token")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disableFullscreenMode()
    }

    private fun enableFullscreenMode() {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.hide()
        }
    }

    private fun disableFullscreenMode() {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.show()
        }
    }
}