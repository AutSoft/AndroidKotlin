package hu.autsoft.example.dogcatalog.data.disk

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction

@Dao
abstract class DogDao {

    @Query("SELECT * FROM dogs")
    abstract fun getAllDogs(): LiveData<List<RoomDog>>

    @Query("DELETE FROM dogs")
    abstract fun removeAllDogs()

    @Insert
    abstract fun addDogs(dogs: List<RoomDog>)

    @Transaction
    open fun setDogs(dogs: List<RoomDog>) {
        removeAllDogs()
        addDogs(dogs)
    }

    @Query("SELECT count(*) FROM dogs")
    abstract fun getDogCount(): Int

}
