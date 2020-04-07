package org.doflamingo.matheffects.view.utils

import android.graphics.Canvas
import android.util.Log

class MapleModel(bean: AnimBean) : BaseAnim(bean) {

    override fun reset() {
        slantRate = bean.direction * random.randomFloat()
        bitmap = bean.resources[random.randomInt(bean.resources.size - 1, true)]
        speedX = random.randomInt(4, true).toDouble() + 5
        constB = (random.randomGaussian() * bean.width).toInt()
        positionY = -100.0
        positionX = bean.width.toDouble()
        if (bean.isReady) {//是否初始化的屏幕中间
            if (random.randomInt(10, true) >= 6) {//取随机值
                positionX = random.randomInt(bean.width / 2).toDouble()
                positionY =bean.height - slantRate * positionX
            }
        }
        direction = random.randomSignum()
        if (speedX == 4.0) {//加快旋转的速度
            direction *= 3
        } else if (speedX == 3.0) {
            direction *= 2
        }
    }

    override fun update() {
        positionX -= speedX
        positionY = bean.height - slantRate * positionX
        degrees = (positionY).rem(360).toFloat()
        if (positionY > bean.height + 100f) {
            reset()
        }
        if (bean.isFading)
            paint.alpha =
                (bean.alphaMin + (bean.alphaMax - bean.alphaMin) * (1 - positionY / bean.height)).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        bitmap?.let {
            matrix.postRotate(
                degrees * direction,
                it.width / 2f,
                it.height / 2f
            )
            matrix.postScale(0.25f, 0.25f)
            matrix.postTranslate(positionX.toFloat()+constB, positionY.toFloat())
            canvas.drawBitmap(it, matrix, paint)
            matrix.reset()
        }
    }
}