package hu.autsoft.example.kotlindemos2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forLoops()
        extensions()
        listExtensions()
        defaultAndNamedParameters()
    }

    private fun extensions() {
        Timber.d("Extensions (clamp)")

        fun Int.clamp(min: Int, max: Int): Int {
            return when {
                this < min -> min
                this > max -> max
                else -> this
            }
        }

        val x: Int = 5
        val y = x.clamp(10, 20)
        Timber.d("Clamped $x to $y")
    }

    private fun forLoops() {
        Timber.d("For loops")

        for (i in 0..10) {
            Timber.d("$i ")
        }

        for (i in 0 until 10 step 2) {
            Timber.d("$i ")
        }

        for (i in 10 downTo 0 step 3) {
            Timber.d("$i ")
        }
    }

    private fun listExtensions() {
        Timber.d("List extensions")

        val numbers: MutableList<Int> = mutableListOf(2, 5, 1, 25, -6, 4, 3)

        Timber.d("Min: ${numbers.min()}")
        Timber.d("Max: ${numbers.max()}")
        Timber.d("Average: ${numbers.average()}")

        Timber.d("Filtered: ${numbers.filter { (it + 2) * 20 % 62 < 25 }}")

        Timber.d("Mapped (doubled): ${numbers.map { it * 2 }}")

        Timber.d("First odd: ${numbers.find { it % 2 == 1 }}")

        Timber.d("Any larger than five: ${numbers.any { it > 5 }}")
        Timber.d("All larger than five: ${numbers.all { it > 5 }}")
    }

    private fun defaultAndNamedParameters() {
        Timber.d("Default and named parameters")

//        fun <T> Iterable<T>.joinToString(
//                separator: CharSequence = ", ",
//                prefix: CharSequence = "",
//                postfix: CharSequence = "",
//                limit: Int = -1,
//                truncated: CharSequence = "...",
//                transform: ((T) -> CharSequence)? = null
//        ): String {
//            ...
//        }

        val months = listOf("January", "February", "March", "April")

        Timber.d(months.joinToString(
                prefix = "{",
                postfix = "}",
                separator = ";"
        ))
    }

}
