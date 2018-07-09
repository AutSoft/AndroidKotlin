package hu.aut.android.highlowgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener{
            val intent = Intent(this@MainActivity,
                    GameActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}
