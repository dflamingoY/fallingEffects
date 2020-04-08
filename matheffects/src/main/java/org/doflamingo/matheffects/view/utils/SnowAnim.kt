package org.doflamingo.matheffects.view.utils

import android.graphics.Canvas
import android.graphics.Color

/**
 * 画小圆点, 向左向右垂直落下
 */
class SnowAnim(bean: AnimBean) : BaseAnim(bean) {
    private var radius = 0f

    override fun reset() {
        slantRate = 3 - random.randomF() * random.randomSignum()
        constB = random.randomInt(bean.width)
        positionY = -100.0
        paint.color = Color.WHITE
        speedX = random.randomDouble(1) + 1
        positionX = random.randomInt(bean.width).toDouble()
        radius = random.randomInt(5, true) + 6f
        if (bean.isReady) {//是否初始化的屏幕中间
            if (random.randomInt(10, true) >= 6) {//取随机值
                positionX = random.randomInt(bean.width / 8).toDouble()
                positionY = slantRate * positionX
            }
        }
    }

    override fun update() {
        positionX += speedX
        positionY = positionX * slantRate
        if (positionY > bean.height) {
            reset()
        }
        if (bean.isFading)
            paint.alpha =
                (bean.alphaMin + (bean.alphaMax - bean.alphaMin) * (1 - positionY / bean.height)).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(positionX.toFloat() + constB, positionY.toFloat(), radius, paint)
    }
}