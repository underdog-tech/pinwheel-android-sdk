package com.underdog_tech.pinwheel_android_demo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.PinwheelFragment
import com.underdog_tech.pinwheel_android.model.*
import com.underdog_tech.pinwheel_android_demo.R
import com.underdog_tech.pinwheel_android_demo.databinding.MainFragmentBinding
import com.underdog_tech.pinwheel_android_demo.repository.model.CapturedEvent
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber

class MainFragment : Fragment(), PinwheelEventListener {

    private lateinit var viewModel: MainViewModel

    val capturedEvents = mutableListOf<CapturedEvent>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun navigateToTheLink(token: String) {
        parentFragmentManager.let {
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

    override fun onSuccess(result: PinwheelResult) {
        Timber.d("ON SUCCESS: %s", result)
    }

    override fun onLogin(result: PinwheelLoginPayload) {
        Timber.d("ON LOGIN: %s", result)
    }

    override fun onLoginAttempt(result: PinwheelLoginAttemptPayload) {
        Timber.d("ON LOGIN ATTEMPT: %s", result)
    }

    override fun onError(error: PinwheelError) {
        Timber.d("ON ERROR: %s", error)
    }

    override fun onExit(error: PinwheelError?) {
        Timber.d("ON EXIT: %s", error)
        parentFragmentManager.popBackStack()
        Toast.makeText(context, "Pinwheel On Exit Event Fired", Toast.LENGTH_LONG).show()

        parentFragmentManager.let {
            val transaction = it.beginTransaction()
            val eventsFragment = EventsFragment(capturedEvents)
            transaction.replace(R.id.rootView, eventsFragment).addToBackStack("events").commit()
        }
    }

    override fun onEvent(eventName: PinwheelEventType, payload: PinwheelEventPayload?) {
        capturedEvents.add(CapturedEvent(eventName.toString(), payload.toString()))
        Timber.d("ON EVENT: %s %s", eventName, payload)
    }

}