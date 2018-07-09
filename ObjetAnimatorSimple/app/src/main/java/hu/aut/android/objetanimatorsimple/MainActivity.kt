package hu.aut.android.objetanimatorsimple

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById<View>(R.id.container)
    }

    fun startAnimation(view: View) {
        when (view.id) {
            R.id.rotateButton -> {
                val animation = ObjectAnimator.ofFloat(view, "rotation", 360f)
                val listener = object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animator: Animator) {
                    }

                    override fun onAnimationEnd(animator: Animator) {
                        view.rotation = 0f
                    }

                    override fun onAnimationCancel(animator: Animator) {
                    }

                    override fun onAnimationStart(animator: Animator) {
                    }
                }
                animation.addListener(listener)
                animation.apply {
                    duration = 2000
                    start()
                }
            }
        }
    }
}
