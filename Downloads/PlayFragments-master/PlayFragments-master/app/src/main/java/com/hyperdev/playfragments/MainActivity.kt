package com.hyperdev.playfragments

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup

import com.hyperdev.playfragments.fragments.ColorFragment
import com.hyperdev.playfragments.fragments.RedFragment

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var mBtnColors: RadioGroup? = null
    private var mColorFragment: ColorFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()

        mColorFragment = ColorFragment()
    }

    private fun findViews() {
        mBtnColors = findViewById(R.id.btnColors) as RadioGroup
        mBtnColors!!.setOnCheckedChangeListener(this)
    }

    fun addDynamicFragment(view: View) {
        val redFragment = RedFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.red_fragment_container, redFragment)
                .commit()
    }

    fun replaceRedWithColorFragment(view: View) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.red_fragment_container, mColorFragment)
                .commit()
    }

    override fun onCheckedChanged(radioGroup: RadioGroup, @IdRes id: Int) {
        when (id) {
            R.id.btnCyan -> mColorFragment!!.changeColor(Color.CYAN)

            R.id.btnGreen -> mColorFragment!!.changeColor(Color.GREEN)

            R.id.btnYellow -> mColorFragment!!.changeColor(Color.YELLOW)
        }
    }
}
