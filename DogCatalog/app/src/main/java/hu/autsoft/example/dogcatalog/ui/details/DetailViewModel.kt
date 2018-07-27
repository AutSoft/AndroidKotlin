package hu.autsoft.example.dogcatalog.ui.details

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import hu.autsoft.example.dogcatalog.data.DogRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DogRepository.getInstance(application)

    private var imageUrl: LiveData<String>? = null

    fun init(breed: String) {
        if (imageUrl == null) {
            imageUrl = repository.getRandomImage(breed)
        }
    }

    fun getImageUrl(): LiveData<String> {
        if (imageUrl == null) {
            throw IllegalStateException("init not called!")
        }
        return imageUrl!!
    }

}
