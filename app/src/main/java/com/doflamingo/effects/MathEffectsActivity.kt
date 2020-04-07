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

        }
        btnSnow.setOnClickListener {

        }
        btnLeaves.setOnClickListener {

        }
        btnFlower.setOnClickListener {

        }
        btnMaples.setOnClickListener {

        }
    }
}