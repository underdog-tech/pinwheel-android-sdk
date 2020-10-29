package com.underdog_tech.pinwheel_android_sdk.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.PinwheelFragment
import com.underdog_tech.pinwheel_android.model.PinwheelActionEvent
import com.underdog_tech.pinwheel_android.model.PinwheelExitEvent
import com.underdog_tech.pinwheel_android.model.PinwheelSuccessEvent
import com.underdog_tech.pinwheel_android_sdk.R
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber

class MainFragment : Fragment(), PinwheelEventListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun navigateToTheLink(token: String) {
        fragmentManager?.let {
            val transaction = it.beginTransaction()
            val pinwheelFragment = PinwheelFragment.newInstance(token)
            pinwheelFragment.pinwheelEventListener = this
            transaction.replace(R.id.rootView, pinwheelFragment).addToBackStack("pinwheel").commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linkButton.setOnClickListener {
            viewModel.generateLinkToken {
                navigateToTheLink(it)
            }
        }
    }

    override fun onSuccess(successEvent: PinwheelSuccessEvent) {
        Timber.d("ON COMPLETE")
    }

    override fun onExit(exitEvent: PinwheelExitEvent) {
        Timber.d("ON EXIT")
        fragmentManager?.popBackStack()
    }

    override fun onEvent(actionEvent: PinwheelActionEvent) {
        Timber.d("ON EVENT")
    }

}