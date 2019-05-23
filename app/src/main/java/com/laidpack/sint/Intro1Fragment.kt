package com.laidpack.sint

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Intro1Fragment : Fragment(), IntroFragment {

    private lateinit var goToNext: () -> Unit
    private lateinit var continueButton : Button
    override fun setGoToNext(method: () -> Unit) {
        goToNext = method
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_intro1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = this.view as View
        continueButton = v.findViewById(R.id.continueButton2)
        continueButton.setOnClickListener {
            goToNext()
        }
    }
}