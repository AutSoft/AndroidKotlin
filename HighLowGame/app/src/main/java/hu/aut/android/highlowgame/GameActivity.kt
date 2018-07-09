package hu.aut.android.highlowgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    private val KEY_GEN = "KEY_GEN"

    private val rand = Random(System.currentTimeMillis())
    private var generated = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnGuess.setOnClickListener{
            try {
                if (etGuess.text.isNotEmpty()) {
                    val num = etGuess.text.toString().toInt()

                    tvStatus.text = when {
                        num < generated -> "Smaller"
                        num > generated -> "Larger"
                        else -> "You have won!"
                    }
                } else {
                    etGuess.error = "This field can not be empty"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                etGuess.error = "Error while processing input"
            }

        }

        generated = savedInstanceState?.getInt(KEY_GEN) ?: generateRandom()

        Log.d("MYLOG", "number: ${generated}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_GEN, generated)
    }

    private fun generateRandom() : Int {
        return rand.nextInt(3)
    }
}
