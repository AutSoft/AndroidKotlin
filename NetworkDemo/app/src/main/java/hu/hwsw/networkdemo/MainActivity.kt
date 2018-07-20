package hu.hwsw.networkdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hu.hwsw.networkdemo.network.GetAstronautsTask
import hu.hwsw.networkdemo.network.OkHttpHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        GetAstronautsTask{ astronauts ->
            mainTextView.text=""

            astronauts.forEach { astronaut ->
                mainTextView.text="${mainTextView.text} \n ${astronaut.name} (${astronaut.craft})"
            }

        }.execute()


    }
}
