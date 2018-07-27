package hu.autsoft.example.dogcatalog.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import hu.autsoft.example.dogcatalog.data.Dog
import hu.autsoft.example.dogcatalog.data.DogRepository

class ListViewModel(context: Context) : ViewModel() {

    private val repository = DogRepository.getInstance(context)

    private var dogs: LiveData<List<Dog>>? = null

    fun init() {
        if (dogs == null) {
            dogs = repository.getDogs()
        }
    }

    fun getDogs(): LiveData<List<Dog>> {
        if (dogs == null) {
            throw IllegalStateException("init not called!")
        }
        return dogs!!
    }

    fun refresh() {
        repository.refreshDogList()
    }

    fun clearDb() {
        repository.clearDb()
    }

}
