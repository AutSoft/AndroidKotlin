package hu.autsoft.example.kotlindemos

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.text = "Valami más"
        button.rotation = -30f
        button.alpha = 0.2f

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mySwitch.toggle()
            }
        })

        val onClick: (View) -> Unit = { view ->
            view.invalidate()
            mySwitch.toggle()
        }

        button.setOnClickListener(onClick)

        25.toString() // "25"

        addFloats(2.toFloat(), 3.toFloat())

        val numbers: MutableList<Int> = mutableListOf<Int>()
        numbers.add(1)
        numbers.add(2)

        val otherNumbers: List<Int> = listOf(1, 2, 3)
        otherNumbers.get(1)
    }

    private fun addFloats(x: Float, y: Float): Float {
        return x + y
    }

//    inner class MyButtonListener : View.OnClickListener {
//        override fun onClick(v: View?) {
//            mySwitch.toggle()
//        }
//    }

}


class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0f, 0f, 0f, 0f, Paint())
    }

}

