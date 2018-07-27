package hu.autsoft.example.dogcatalog.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import hu.autsoft.example.dogcatalog.AppExecutors
import hu.autsoft.example.dogcatalog.data.disk.DogDao
import hu.autsoft.example.dogcatalog.data.disk.DogDatabase
import hu.autsoft.example.dogcatalog.data.disk.RoomDog
import hu.autsoft.example.dogcatalog.data.network.DogApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class DogRepository(context: Context) {

    companion object {
        private const val KEY_LAST_FETCH = "KEY_LAST_FETCH"
        private const val DATA_MAX_AGE = 10_000L

        private var instance: DogRepository? = null

        fun getInstance(context: Context): DogRepository {
            if (instance == null) {
                instance = DogRepository(context)
            }
            return instance!!
        }
    }

    private val dogDao: DogDao =
            Room.databaseBuilder(context, DogDatabase::class.java, "dogs")
                    .build()
                    .dogDao()

    private val dogApi: DogApi =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://dog.ceo/api/")
                    .build()
                    .create(DogApi::class.java)

    private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("repository", Context.MODE_PRIVATE)

    fun getDogs(): LiveData<List<Dog>> {
        AppExecutors.diskIO.execute {
            if (isUpdateNeeded()) {
                refreshDogList()
            }
        }

        Timber.d("Dog livedata fetched from Room")

        return Transformations.map(dogDao.getAllDogs()) { roomDogs ->
            roomDogs.map { Dog(breed = it.breed) }
        }
    }

    private fun isUpdateNeeded(): Boolean {
        val lastFetch = sharedPreferences.getLong(KEY_LAST_FETCH, 0L)
        val timeSinceLastFetch = System.currentTimeMillis() - lastFetch
        if (timeSinceLastFetch > DATA_MAX_AGE) {
            Timber.d("isUpdateNeeded: Data too old, fetched ${timeSinceLastFetch / 1000} seconds ago")
            return true
        }
        if (dogDao.getDogCount() == 0) {
            Timber.d("isUpdateNeeded: No dogs in DB")
            return true
        }

        Timber.d("isUpdateNeeded: Dog list up to date")
        return false
    }

    fun refreshDogList() {
        AppExecutors.networkIO.execute {
            Timber.d("Fetching dogs from network")

            val response = dogApi.getAllBreeds().execute()
            val responseBody = response.body()

            if (responseBody != null) {
                val dogs = responseBody.breedsAndSubBreeds.keys.map { name -> RoomDog(null, name) }

                AppExecutors.diskIO.execute {
                    Timber.d("Saving network dogs to DB")

                    dogDao.setDogs(dogs)

                    sharedPreferences.edit {
                        putLong(KEY_LAST_FETCH, System.currentTimeMillis())
                    }
                }
            }
        }
    }

    fun getRandomImage(breed: String): LiveData<String> {
        val pictureLiveData = MutableLiveData<String>()

        AppExecutors.networkIO.execute {
            Timber.d("Fetching random dog picture from network")

            val response = dogApi.getRandomPicture(breed).execute().body()

            if (response != null) {
                pictureLiveData.postValue(response.imageUrl)
            }
        }

        return pictureLiveData
    }

    fun clearDb() {
        AppExecutors.diskIO.execute {
            Timber.d("Clearing DB")
            dogDao.removeAllDogs()
        }
    }

}
