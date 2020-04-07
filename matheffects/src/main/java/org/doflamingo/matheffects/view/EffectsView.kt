package org.doflamingo.matheffects.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.doflamingo.matheffects.R
import org.doflamingo.matheffects.view.utils.*

class EffectsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mData: Array<BaseAnim>? = null
    private lateinit var bean: AnimBean
    private var model: String = ""
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bean = AnimBean(
            1,
            1,
            0f,
            arrayOf(
                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_maples_1),
//                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_2),
//                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_3),
//                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_4),
                BitmapFactory.decodeResource(context.resources, R.mipmap.icon_maples_2)
            ),
            w,
            h,
            120,
            255,
            25,
            40
        )
        mData = Array(20) { MapleModel(bean) }
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

    fun start(model: String) {


    }

}