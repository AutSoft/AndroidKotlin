package hu.autsoft.example.dogcatalog.ui.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import hu.autsoft.example.dogcatalog.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_DOG_BREED = "KEY_DOG_BREED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val factory = ViewModelProvider.AndroidViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)

        val breed: String = intent?.getStringExtra(KEY_DOG_BREED)
                ?: throw IllegalStateException("DetailActivity started without breed parameter!")

        viewModel.init(breed)
        viewModel.getImageUrl().observe(this, Observer { imageUrl ->
            Glide.with(this).load(imageUrl).into(ivImage)
        })

        tvBreed.text = breed
    }

}
