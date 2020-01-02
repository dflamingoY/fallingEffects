package com.doflamingo.effects.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.doflamingo.effects.R
import java.util.*
import kotlin.collections.ArrayList

class EffectsView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val list = ArrayList<EffectsModel>()
    private var isStarted = false
    private var modelCount = 20
    private var speedMin = 0
    private var speedMax = 0
    private var sizeMin = 0
    private var sizeMax = 0
    private var alphaMin = 255
    private var alphaMax = 255
    private var angle = 0
    private var enableAlpha = false

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.EffectsView)
        modelCount = array.getInt(R.styleable.EffectsView_modelCount, 20)
        speedMin = (array.getDimension(R.styleable.EffectsView_modelSpeedMin, 1f) + 0.5f).toInt()
        speedMax = (array.getDimension(R.styleable.EffectsView_modelSpeedMax, 1f) + 0.5f).toInt()
        sizeMin = (array.getDimension(R.styleable.EffectsView_modelSizeMin, 10f) + 0.5f).toInt()
        sizeMax = (array.getDimension(R.styleable.EffectsView_modelSizeMax, 10f) + 0.5f).toInt()
        alphaMin = array.getInt(R.styleable.EffectsView_modelAlphaMin, 255)
        alphaMax = array.getInt(R.styleable.EffectsView_modelAlphaMax, 255)
        angle = array.getInt(R.styleable.EffectsView_modelAngle, 0)
        enableAlpha = array.getBoolean(R.styleable.EffectsView_modelAlphaEnable, false)
        array.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        if (isStarted) {
            list.forEach {
                it.onDraw(canvas)
            }
            postInvalidateOnAnimation()
            list.forEach {
                it.update()
            }
        }
    }

    /**
     * 传入图片资源
     */
    fun start(
        images: Array<Int>,
        angle: Int? = null,
        speedMin: Int? = null,
        speedMax: Int? = null
    ) {
        if (modelCount == 0) {
            return
        }
        if (list.isEmpty()) {//创建item
            val bean = EffectsModel.EffectBean(
                measuredWidth,
                measuredHeight + 300,//这里是为了超出屏幕高度处理, 可以自己计算
                Array(images.size) {
                    BitmapFactory.decodeResource(resources, images[it])
                },
                angle ?: this.angle,
                alphaMin,
                alphaMax,
                enableAlpha,
                speedMin ?: this.speedMin,
                speedMax ?: this.speedMax,
                sizeMin,
                sizeMax,
                Random()
            )
            repeat((0 until modelCount).count()) {
                list.add(EffectsModel(bean))
            }
        } else {
            list[0].bean.let { bean ->
                try {
                    bean.images.forEach {
                        it.recycle()
                    }
                } catch (e: Exception) {
                }
                bean.images = Array(images.size) {
                    BitmapFactory.decodeResource(resources, images[it])
                }
                bean.angle = angle ?: this.angle
                bean.speedMin = speedMin ?: this.speedMin
                bean.speedMax = speedMax ?: this.speedMax
            }
        }
        isStarted = true
        postInvalidateOnAnimation()
    }

    fun stop() {
        isStarted = false
    }

}