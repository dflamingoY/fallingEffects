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
    private var start = false

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bean = AnimBean(
            1,
            1,
            0f,
            arrayOf(
            ),
            w,
            h,
            120,
            255,
            25,
            40
        )
    }

    override fun onDraw(canvas: Canvas) {
        if (start) {
            mData?.forEach {
                it.onDraw(canvas)
            }
            mData?.forEach {
                it.update()
            }
            postInvalidateOnAnimation()
        }
    }

    fun start(model: String) {
        if (this.model == model) {
            if (start)
                return
            else {
                start = true
                postInvalidateOnAnimation()
                return
            }
        }
        mData = null
        this.model = model
        start = true
        when (model) {
            "rain" -> {
                bean.resources = arrayOf(
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_rain_1)
                )
                mData = Array(10) { RainModel(bean) }
            }
            "snow" -> {
                bean.resources = arrayOf()
                mData = Array(10) { SnowAnim(bean) }
            }
            "leaves" -> {
                bean.resources = arrayOf(
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_leaves_1),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_leaves_2),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_leaves_3),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_leaves_4),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_leaves_5)
                )
                mData = Array(20) { AnimModel(bean) }
            }
            "maple" -> {
                bean.resources = arrayOf(
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_maples_1),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_maples_2)
                )
                mData = Array(10) { MapleModel(bean) }
            }
            "flower" -> {
                bean.resources = arrayOf(
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_1),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_2),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_3),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_4),
                    BitmapFactory.decodeResource(context.resources, R.mipmap.icon_flower_5)
                )
                mData = Array(20) { FlowerModel(bean) }
            }
            else -> {
                start = false
            }
        }
        postInvalidateOnAnimation()
    }

}