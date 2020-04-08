package com.doflamingo.effects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_math_effect.*

class MathEffectsActivity : AppCompatActivity(R.layout.activity_math_effect) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnEmpty.setOnClickListener {
            effectsView.start("")
        }
        btnRain.setOnClickListener {
            ivBg.setImageResource(R.mipmap.icon_rain_first_frame)
            effectsView.start("rain")
        }
        btnSnow.setOnClickListener {
            ivBg.setImageResource(R.mipmap.icon_snow_bg)
            effectsView.start("snow")
        }
        btnLeaves.setOnClickListener {
            ivBg.setImageResource(R.mipmap.icon_leaves_first_frame)
            effectsView.start("leaves")
        }
        btnFlower.setOnClickListener {
            ivBg.setImageResource(R.mipmap.icon_flower_first_frame)
            effectsView.start("flower")
        }
        btnMaples.setOnClickListener {
            ivBg.setImageResource(R.mipmap.icon_maple_first_frame)
            effectsView.start("maple")
        }
    }
}