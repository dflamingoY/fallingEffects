package org.doflamingo.matheffects.view.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint

abstract class BaseAnim(val bean: AnimBean) {
    protected var bitmap: Bitmap? = null
    protected val paint = Paint()
    protected var matrix = Matrix()
    protected var positionX: Double = 0.0
    protected var positionY: Double = 0.0
    protected val random = Randomizer()
    protected var degrees = 0f
    protected var slantRate: Float = 0f
    protected var constB: Int = 0
    protected var speedX: Double = 0.0
    protected var direction: Int = 1
    protected var rotationSpeed = 1

    init {
        reset()
    }

    abstract fun reset()

    abstract fun update()

    abstract fun onDraw(canvas: Canvas)

}

/**
 * @param direction 方向 0 left -> right 1 right->left  up->down
 * @param rotation 旋转的方向， 0 顺时针， 1 逆时针
 *@param rotationRate 旋转系数
 * */
data class AnimBean(
    val direction: Int,
    val rotation: Int,
    val rotationRate: Float,
    val resources: Array<Bitmap>,
    val width: Int,
    val height: Int,
    val alphaMin: Int,
    val alphaMax: Int,
    val speedMin: Int,
    val speedMax: Int,
    val isReady: Boolean = true,
    val isFading: Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnimBean) return false

        if (direction != other.direction) return false
        if (rotation != other.rotation) return false
        if (rotationRate != other.rotationRate) return false
        if (!resources.contentEquals(other.resources)) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (alphaMin != other.alphaMin) return false
        if (alphaMax != other.alphaMax) return false
        if (speedMin != other.speedMin) return false
        if (speedMax != other.speedMax) return false
        if (isReady != other.isReady) return false
        if (isFading != other.isFading) return false

        return true
    }

    override fun hashCode(): Int {
        var result = direction
        result = 31 * result + rotation
        result = 31 * result + rotationRate.hashCode()
        result = 31 * result + resources.contentHashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + alphaMin
        result = 31 * result + alphaMax
        result = 31 * result + speedMin
        result = 31 * result + speedMax
        result = 31 * result + isReady.hashCode()
        result = 31 * result + isFading.hashCode()
        return result
    }
}