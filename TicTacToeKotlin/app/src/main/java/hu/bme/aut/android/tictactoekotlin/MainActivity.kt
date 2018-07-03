package hu.bme.aut.android.tictactoekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import hu.bme.aut.android.tictactoekotlin.data.Car
import hu.bme.aut.android.tictactoekotlin.data.Ship
import hu.bme.aut.android.tictactoekotlin.data.TicTacToeModel
import hu.bme.aut.android.tictactoekotlin.view.TicTacToeView
import java.util.concurrent.locks.Lock

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ticTacToeView = findViewById(R.id.ticTacToeView) as TicTacToeView

        (findViewById(R.id.btnRestart) as Button).setOnClickListener {
            ticTacToeView.resetGame()
        }

    }

    private fun TicTacToeView.resetGame() {
        TicTacToeModel.resetModel()
        invalidate()
    }

}
