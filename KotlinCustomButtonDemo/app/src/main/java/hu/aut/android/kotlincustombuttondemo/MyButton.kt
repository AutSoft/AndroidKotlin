package hu.aut.android.kotlincustombuttondemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.view.MotionEvent


class MyButton(context: Context?, attrs: AttributeSet?) : Button(context, attrs) {

    private var counter = 0
    private var paintText = Paint()

    init {
        paintText?.color = Color.RED
        paintText?.textSize = 40f

        val typedArray = context?.theme?.obtainStyledAttributes(
                attrs, R.styleable.MyButton, 0, 0)
        try {
            paintText.color = typedArray!!.getColor(
                    R.styleable.MyButton_counter_color,
                    Color.RED)
        } finally {
            typedArray?.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText("${counter}", 15f, 45f, paintText);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            counter++
            invalidate()
        }

        return super.onTouchEvent(event)
    }


}