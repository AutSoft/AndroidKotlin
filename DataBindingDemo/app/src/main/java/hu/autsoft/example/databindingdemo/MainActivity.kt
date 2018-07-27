package hu.autsoft.example.databindingdemo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hu.autsoft.example.databindingdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    data class User(var firstName: String, var lastName: String)

    companion object {
        private val FIRST_NAMES = listOf(
                "Dwight", "Jim", "Pam", "Michael", "Toby", "Andy", "Ryan",
                "Robert", "Jan", "Stanley", "Kevin", "Meredith", "Angela",
                "Oscar", "Phyllis", "Kelly", "Creed", "Darryl", "Holly"
        )
        private val LAST_NAMES = listOf(
                "Schrute", "Halpert", "Beesly", "Scott", "Flenderson", "Bernard", "Howard",
                "California", "Levinson", "Hudson", "Malone", "Palmer", "Martin",
                "Martinez", "Lapin", "Kapoor", "Bratton", "Philbin", "Flax"
        )
    }

    private val random = Random()
    private fun <T> List<T>.getRandomElement(): T = get(random.nextInt(size))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = User(FIRST_NAMES.getRandomElement(), LAST_NAMES.getRandomElement())

        btnShuffle.setOnClickListener {
            binding.user = User(FIRST_NAMES.getRandomElement(), LAST_NAMES.getRandomElement())
        }
    }

}
