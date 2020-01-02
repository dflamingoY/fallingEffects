package com.doflamingo.effects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart.setOnClickListener {
            effects.start(
                when (count.rem(3)) {
                    0 ->
                        arrayOf(
                            R.mipmap.icon_effedts_flower_1,
                            R.mipmap.icon_effedts_flower_2,
                            R.mipmap.icon_effedts_flower_3,
                            R.mipmap.icon_effedts_flower_4
                        )
                    1 -> arrayOf(
                        R.mipmap.icon_effects_snow_1,
                        R.mipmap.icon_effects_snow_2
                    )
                    else -> arrayOf(
                        R.mipmap.icon_effedts_leaves_1,
                        R.mipmap.icon_effedts_leaves_2,
                        R.mipmap.icon_effedts_leaves_3,
                        R.mipmap.icon_effedts_leaves_4
                    )
                }
            )
            count++
        }
        btnEnd.setOnClickListener {
            effects.stop()
        }
    }
}
