package hu.autsoft.example.dogcatalog

import android.app.Application
import timber.log.Timber

class DogApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

}
