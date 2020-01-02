package com.doflamingo.effects.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class EffectsModel(val bean: EffectBean) {
    private var bitmap: Bitmap? = null
    private var positionX = 0.0
    private var positionY = 0.0
    private var alpha = 255
    private var speedX = 0.0
    private var speedY = 0.0
    private var size = 0

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.rgb(255, 255, 255)
    }

    init {
        reset()
    }

    private fun reset(positionY: Double? = null) {
        size = bean.sizeMin + bean.random.nextInt(bean.sizeMax - bean.sizeMin) + 1
        if (bean.images.isNotEmpty()) {
            var bWidth: Int
            var bHeight: Int
            bitmap =
                Bitmap.createScaledBitmap(bean.images[bean.random.nextInt(bean.images.size)].let {
                    bWidth = it.width
                    bHeight = it.height
                    it
                }, (bWidth / 2f + 0.5f).toInt(), (bHeight / 2f + 0.5f).toInt(), false)
        }
        val speed =
            (size - bean.sizeMin).toFloat() / (bean.sizeMax - bean.sizeMin).toFloat() * (bean.speedMax - bean.speedMin).toFloat() + bean.speedMin
        val radians =
            Math.toRadians(bean.angle * bean.random.nextDouble()) * if (bean.random.nextBoolean()) 1 else -1
        speedX = sin(radians) * speed
        speedY = cos(radians) * speed
        positionX = bean.random.nextDouble() * bean.width
        alpha = bean.alphaMin + bean.random.nextInt(bean.alphaMax - bean.alphaMin) + 1
        if (bean.alphaEnable) {
            paint.alpha = alpha
        }
        if (positionY != null) {
            this.positionY = positionY
        } else {
            this.positionY = bean.random.nextDouble() * bean.height
        }
    }

    fun update() {
        positionX += speedX
        positionY += speedY
        if (positionY > bean.height) {
            reset(-size.toDouble())
        }
        if (bean.alphaEnable) {
            paint.alpha = alpha
        }
    }

    fun onDraw(canvas: Canvas) {
        bitmap?.let {
            canvas.drawBitmap(it, positionX.toFloat(), positionY.toFloat() - 300, null)
        }
    }

    data class EffectBean(
        val width: Int,
        val height: Int,
        var images: Array<Bitmap>,
        var angle: Int,
        val alphaMin: Int,
        val alphaMax: Int,
        val alphaEnable: Boolean,
        var speedMin: Int,
        var speedMax: Int,
        val sizeMin: Int,
        val sizeMax: Int,
        val random: Random
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is EffectBean) return false

            if (width != other.width) return false
            if (height != other.height) return false
            if (!images.contentEquals(other.images)) return false
            if (angle != other.angle) return false
            if (alphaMin != other.alphaMin) return false
            if (alphaMax != other.alphaMax) return false
            if (alphaEnable != other.alphaEnable) return false
            if (speedMin != other.speedMin) return false
            if (speedMax != other.speedMax) return false
            if (sizeMin != other.sizeMin) return false
            if (sizeMax != other.sizeMax) return false

            return true
        }

        override fun hashCode(): Int {
            var result = width
            result = 31 * result + height
            result = 31 * result + images.contentHashCode()
            result = 31 * result + angle
            result = 31 * result + alphaMin
            result = 31 * result + alphaMax
            result = 31 * result + alphaEnable.hashCode()
            result = 31 * result + speedMin
            result = 31 * result + speedMax
            result = 31 * result + sizeMin
            result = 31 * result + sizeMax
            return result
        }
    }
}