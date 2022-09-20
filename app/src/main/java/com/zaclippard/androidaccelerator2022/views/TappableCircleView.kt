package com.zaclippard.androidaccelerator2022.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min

class TappableCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val primaryColor = Color.YELLOW
    private val secondaryColor = Color.BLUE
    private var usePrimaryColor = true
    private var size: Int = 0

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnClickListener {
            usePrimaryColor = usePrimaryColor.not()
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
    }

    private fun determineMeasuredSize() {
        size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = if (usePrimaryColor) primaryColor else secondaryColor
        paint.style = Paint.Style.FILL

        val radius = size / 2f

        canvas.drawCircle(size / 2f, size / 2f, radius, paint)
    }

    override fun onSaveInstanceState(): Parcelable {
        val parentParcelable = super.onSaveInstanceState()
        return Bundle().apply {
            putParcelable(KEY_PARENT_PARCELABLE, parentParcelable)
            putBoolean(KEY_USE_PRIMARY_COLOR, usePrimaryColor)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val bundle = state as Bundle
        usePrimaryColor = bundle.getBoolean(KEY_USE_PRIMARY_COLOR)
        super.onRestoreInstanceState(bundle.getParcelable(KEY_PARENT_PARCELABLE))
    }

    companion object {
        private const val TAG = "TappableCircleView"
        private const val KEY_USE_PRIMARY_COLOR = "usePrimaryColor"
        private const val KEY_PARENT_PARCELABLE = "parentParcelable"
    }
}
