package hu.bme.aut.android.tictactoekotlin.data

import android.util.Log

class Car constructor(type: String) {
    val typeUpper = type.toUpperCase()

    init {
        Log.d("TAG_DEMO","Car created: ${type}")
    }

    constructor(type: String, model: String) : this(type) {
        Log.d("TAG_DEMO","Car model: ${model}")
    }
}