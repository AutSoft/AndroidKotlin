package hu.aut.android.activitylifecycledemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG_LIFE = "TAG_LIFE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDetails.setOnClickListener{
            val intentStart = Intent()
            intentStart.setClass(this@MainActivity,
                    DetailsActivity::class.java
                    )
            startActivity(intentStart)
        }

        Log.d(TAG_LIFE, "onCreate started")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LIFE, "onStart started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LIFE, "onResume started")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LIFE, "onPause started")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_LIFE, "onStop started")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LIFE, "onDestroy started")
    }
}
