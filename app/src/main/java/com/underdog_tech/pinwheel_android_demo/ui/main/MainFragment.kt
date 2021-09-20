package com.underdog_tech.pinwheel_android_demo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.underdog_tech.pinwheel_android.PinwheelEventListener
import com.underdog_tech.pinwheel_android.PinwheelFragment
import com.underdog_tech.pinwheel_android.model.*
import com.underdog_tech.pinwheel_android_demo.R
import com.underdog_tech.pinwheel_android_demo.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber

class MainFragment : Fragment(), PinwheelEventListener {

    private lateinit var viewModel: MainViewModel

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
        val action = MainFragmentDirections.actionMainFragment2ToPinwheelFragment(token)
        val navController = findNavController()
        navController.navigate(action)
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

    override fun onError(error: PinwheelError) {
        Timber.d("ON ERROR: %s", error)
    }

    override fun onExit(error: PinwheelError?) {
        Timber.d("ON EXIT: %s", error)
        val navController = findNavController()
        navController.popBackStack()
        Toast.makeText(context, "Pinwheel On Exit Event Fired", Toast.LENGTH_LONG).show()
    }

    override fun onEvent(eventName: PinwheelEventType, payload: PinwheelEventPayload?) {
        Timber.d("ON EVENT: %s %s", eventName, payload)
    }

}