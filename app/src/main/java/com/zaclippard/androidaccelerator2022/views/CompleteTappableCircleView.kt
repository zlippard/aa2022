package com.zaclippard.androidaccelerator2022.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlin.math.min
import kotlin.random.Random

//sealed class NetworkResult {
//    data class Success(val articles: List<Article>) : NetworkResult()
//    data class Failure(val errorMessage: String) : NetworkResult()
//}
//
//val result: NetworkResult = getResult()
//
//when (result) {
//    is NetworkResult.Success -> { }
//    is NetworkResult.Failure -> { }
//}
//
//fun getResult(): NetworkResult

//private enum class CircleViewColor(val innerColor: Int) {
//    YELLOW(Color.YELLOW),
//    BLUE(Color.BLUE),
//    GREEN(Color.GREEN),
//}

// Add 'kotlin-parcelize' plugin to app/build.gradle
@Parcelize
sealed class CircleViewColor(val innerColor: Int) : Parcelable {
    object Yellow : CircleViewColor(Color.YELLOW)
    object Blue : CircleViewColor(Color.BLUE)
    data class Green(
        private val num: Int
    ) : CircleViewColor(Color.GREEN) {
        @IgnoredOnParcel
        val text = "This is green! num = $num"
    }
}

class CompleteTappableCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var color: CircleViewColor = CircleViewColor.Yellow
    private var size: Int = 0
    private val randomRange = (0..10_000)

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnClickListener {
//            color = when (color) {
//                CircleViewColor.YELLOW -> CircleViewColor.BLUE
//                CircleViewColor.BLUE -> CircleViewColor.GREEN
//                CircleViewColor.GREEN -> {
//                    CircleViewColor.YELLOW
//                }
//            }
            color = when (color) {
                is CircleViewColor.Yellow -> CircleViewColor.Blue
                is CircleViewColor.Blue -> CircleViewColor.Green(randomRange.random())
                is CircleViewColor.Green -> CircleViewColor.Yellow
            }
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i(TAG, "onMeasure()")
        determineMeasuredSize()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i(TAG, "onLayout()")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.i(TAG, "onDraw()")
        drawCircle(canvas)

        val localColor = color
        if (localColor is CircleViewColor.Green) {
            drawText(canvas, localColor.text)
        }
    }

    private fun determineMeasuredSize() {
        size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = color.innerColor
        paint.style = Paint.Style.FILL

        val radius = size / 2f

        canvas.drawCircle(size / 2f, size / 2f, radius, paint)
    }

    private fun drawText(canvas: Canvas, text: String) {
        val textPaint = Paint()
        textPaint.color = Color.WHITE
        textPaint.textSize = 48f
        textPaint.textAlign = Paint.Align.CENTER
        canvas.drawText(text, size / 2f, size / 2f, textPaint)
    }

    override fun onSaveInstanceState(): Parcelable {
        val parentParcelable = super.onSaveInstanceState()
        return Bundle().apply {
            putParcelable(KEY_PARENT_PARCELABLE, parentParcelable)
//            putSerializable(KEY_COLOR, color)
            putParcelable(KEY_COLOR, color)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val bundle = state as Bundle
//        color = bundle.getSerializable(KEY_COLOR) as CircleViewColor
        color = bundle.getParcelable(KEY_COLOR)!!
        super.onRestoreInstanceState(bundle.getParcelable(KEY_PARENT_PARCELABLE))
    }

    companion object {
        private const val TAG = "TappableCircleView"
        private const val KEY_COLOR = "color"
        private const val KEY_PARENT_PARCELABLE = "parentParcelable"
    }
}
