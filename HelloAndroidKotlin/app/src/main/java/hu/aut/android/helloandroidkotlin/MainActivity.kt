package hu.aut.android.helloandroidkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTime.setOnClickListener{
            val date: String = Date(System.currentTimeMillis()).toString()
            tvData.text = date
            Toast.makeText(this@MainActivity,
                    date, Toast.LENGTH_LONG).show()
        }
    }

}
