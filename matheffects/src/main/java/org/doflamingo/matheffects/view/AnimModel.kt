package org.doflamingo.matheffects.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.Log
import org.doflamingo.matheffects.view.utils.Randomizer
import kotlin.math.abs

/**
 * f(x)=ax+b  b∈{0，width}
 *
 */
class AnimModel(context: Context, private val bean: AnimBean) {

    private var bitmap: Bitmap? = null
    private var matrix = Matrix()
    private val paint = Paint()
    private var positionX: Double = 0.0
    private var positionY: Double = 0.0
    private val random = Randomizer()
    private var degrees = 0f
    private var slantRate: Float = 0f
    private var constB: Int = 0
    private var speedX: Double = 0.0

    init {
        reset()
    }

    /*
     * 初始化公式
     */
    private fun reset() {
        slantRate = random.randomGaussian().let {
            if (abs(it) < bean.height.toFloat() / bean.width) {
                (bean.height.toFloat() / bean.width).toFloat()
            } else {
                abs(it.toFloat())
            }
        }
        Log.d("Mozator", "slantRate : $slantRate")
        constB = random.randomInt(bean.width / 2)
        positionY = 0.0
        positionX = 0.0
        bitmap = bean.resources[random.randomInt(bean.resources.size - 1)]
        speedX = random.randomInt(5, false).toDouble() + 1
    }

    /**
     * 计算x，y轴的位移
     */
    fun update() {
        positionX += speedX
        positionY = slantRate * positionX
        degrees = (positionY).rem(360).toFloat()
        if (positionY > bean.height) {
            reset()
        }
    }

    fun onDraw(canvas: Canvas) {
        bitmap?.let {
            matrix.postRotate(
                degrees * if (bean.rotation == 1) -1 else 1,
                it.width / 2f,
                it.height / 2f
            )
            matrix.postTranslate(positionX.toFloat() + constB, positionY.toFloat())
            canvas.drawBitmap(it, matrix, paint)
            matrix.reset()
        }
    }

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
    val isFalling: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AnimBean

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
        if (isFalling != other.isFalling) return false

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
        result = 31 * result + isFalling.hashCode()
        return result
    }
}