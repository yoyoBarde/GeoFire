package com.hyperdev.playfragments.fragments


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hyperdev.playfragments.R

/**
 * A simple [Fragment] subclass.
 */
class ColorFragment : Fragment() {


    private var mColorView: View? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_color, container, false)

        mColorView = view.findViewById(R.id.colorView)

        return view
    }

    fun changeColor(color: Int) {
        mColorView!!.setBackgroundColor(color)
    }
}// Required empty public constructor
