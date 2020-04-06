package org.doflamingo.matheffects.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.doflamingo.matheffects.R

class EffectsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mData: Array<AnimModel>? = null


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val bean = AnimBean(
            1,
            1,
            0f,
            arrayOf(
                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_effedts_leaves_1),
                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_effedts_leaves_2),
                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_effedts_leaves_3),
                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_effedts_leaves_4)
            ),
            w,
            h,
            255,
            255,
            10,
            20
        )
        mData = Array(20) { AnimModel(context, bean) }
    }

    override fun onDraw(canvas: Canvas) {
        mData?.forEach {
            it.onDraw(canvas)
        }
        mData?.forEach {
            it.update()
        }
        postInvalidateOnAnimation()
    }

    fun start() {

    }

}