package kotlintanfolyam.autsoft.hu.objectanimatordemo

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import java.util.*


class MainActivity : Activity() {

    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById<View>(R.id.container)
    }

    fun startAnimation(view: View) {
        when (view.id) {
            R.id.colorButton -> {
                val colorAnimation = ObjectAnimator.ofObject(container,
                        "backgroundColor",
                        ArgbEvaluator(),
                        container.solidColor,
                        getRandomColor())
                colorAnimation.apply {
                    duration = 500
                    start()
                }
            }
            R.id.rotateButton -> {
                val animation = ObjectAnimator.ofFloat(view, "rotation", 360f)
                animation.apply {
                    duration = 500
                    doOnEnd {
                        view.rotation = 0f
                    }
                    start()
                }
            }
            R.id.animatorSet -> {
                val colorAnimation = ObjectAnimator.ofObject(container,
                        "backgroundColor",
                        ArgbEvaluator(),
                        container.solidColor,
                        getRandomColor())
                val translationAnimation = ObjectAnimator.ofFloat(view, "translationY", view.translationY + 200)
                val animatorSet = AnimatorSet()
                animatorSet.apply {
                    playTogether(colorAnimation, translationAnimation)
                    duration
                    start()
                }
            }
        }
    }

    private fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256))
    }

}

fun Animator.doOnEnd(action: (animator: Animator) -> Unit) = addListener(onEnd = action)

fun Animator.addListener(
        onEnd: ((animator: Animator) -> Unit)? = null,
        onStart: ((animator: Animator) -> Unit)? = null,
        onCancel: ((animator: Animator) -> Unit)? = null,
        onRepeat: ((animator: Animator) -> Unit)? = null
): Animator.AnimatorListener {
    val listener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) {
            onRepeat?.invoke(animator)
        }

        override fun onAnimationEnd(animator: Animator) {
            onEnd?.invoke(animator)
        }

        override fun onAnimationCancel(animator: Animator) {
            onCancel?.invoke(animator)
        }

        override fun onAnimationStart(animator: Animator) {
            onStart?.invoke(animator)
        }
    }
    addListener(listener)
    return listener
}
