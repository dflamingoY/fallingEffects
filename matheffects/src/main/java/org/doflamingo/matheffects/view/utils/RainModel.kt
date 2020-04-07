package org.doflamingo.matheffects.view.utils

import android.graphics.Canvas

class RainModel(bean: AnimBean) : BaseAnim(bean) {

    override fun reset() {
        positionY = -100.0
        bitmap = bean.resources[random.randomInt(bean.resources.size - 1)]
        positionX = random.randomInt(bean.width).toDouble()
        speedX = (random.randomInt(bean.speedMax - bean.speedMin) + bean.speedMin).toDouble()
    }

    override fun update() {
        positionY += speedX
        if (positionY > bean.height) {
            reset()
        }
        if (bean.isFading)
            paint.alpha =
                (bean.alphaMin + (bean.alphaMax - bean.alphaMin) * (1 - positionY / bean.height)).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        bitmap?.let {
            matrix.postScale(0.5f, 0.5f)
            matrix.postTranslate(positionX.toFloat() - 100f, positionY.toFloat())
            canvas.drawBitmap(it, matrix, paint)
            matrix.reset()
        }
    }
}