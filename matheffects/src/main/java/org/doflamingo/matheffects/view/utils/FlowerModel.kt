package org.doflamingo.matheffects.view.utils

import android.graphics.Canvas

class FlowerModel(bean: AnimBean) : BaseAnim(bean) {

    override fun reset() {
        constB = 0
        slantRate = random.randomFloat(0f)
        constB = (random.randomGaussian() * bean.width).toInt()
        positionY = -100.0
        positionX = 0.0
        bitmap = bean.resources[random.randomInt(bean.resources.size - 1)]
        speedX = random.randomInt(4, true).toDouble() + 2
        rotationSpeed = random.randomInt(4)
        if (rotationSpeed == 0) rotationSpeed = 1
        direction = random.randomSignum()
        if (bean.isReady) {//是否初始化的屏幕中间
            if (random.randomInt(10, true) >= 6) {//取随机值
                positionX = random.randomInt(bean.width / 2).toDouble()
                positionY = slantRate * positionX
            }
        }
//        if (speedX == 4.0) {//加快旋转的速度
//            direction *= 3
//        } else if (speedX == 3.0) {
//            direction *= 2
//        }
    }

    override fun update() {
        positionX += speedX
        positionY = slantRate * positionX
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
            matrix.postScale(0.3f, 0.3f)
            matrix.postTranslate(positionX.toFloat() + constB, positionY.toFloat() - 100f)
            canvas.drawBitmap(it, matrix, paint)
            matrix.reset()
        }
    }
}