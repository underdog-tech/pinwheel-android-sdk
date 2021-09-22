package com.underdog_tech.pinwheel_android_demo.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.underdog_tech.pinwheel_android_demo.R
import com.underdog_tech.pinwheel_android_demo.repository.model.CapturedEvent

class EventsFragment(val capturedEvents: MutableList<CapturedEvent>) : Fragment(R.layout.events_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.events_fragment, container, false)
        val listView = view.findViewById<ListView>(R.id.events_listview)
        if (listView != null) {
            listView.adapter = EventsAdapter(inflater.context, capturedEvents)
        }
        return view
    }


    private class EventsAdapter(context: Context, events: MutableList<CapturedEvent>) : BaseAdapter() {
        private val mContext: Context = context
        private val mEvents: MutableList<CapturedEvent> = events

        override fun getCount(): Int {
            return mEvents.count()
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        override fun getItem(position: Int): String {
            return mEvents[position].eventName.toString()
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var tv = TextView(mContext)
            val event = mEvents.get(position)
            tv.text = event.eventName + " - " + event.payload
            return tv
        }
    }
}