package hu.aut.android.kotlinpong.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PongView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paintBg: Paint = Paint()
    private val paintLine: Paint = Paint()

    init {
        paintBg.color = Color.BLACK
        paintBg.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 7f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBg)

        canvas?.drawLine(0f, 0f, width.toFloat(), height.toFloat(),
                paintLine)

        canvas?.drawCircle(150f, 150f, 60f, paintLine)
    }

}