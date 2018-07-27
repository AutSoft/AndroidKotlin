package hu.autsoft.example.dogcatalog.data.disk

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dogs")
data class RoomDog(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val breed: String
)
