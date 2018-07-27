package hu.autsoft.example.dogcatalog.data.disk

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [RoomDog::class], version = 1)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

}