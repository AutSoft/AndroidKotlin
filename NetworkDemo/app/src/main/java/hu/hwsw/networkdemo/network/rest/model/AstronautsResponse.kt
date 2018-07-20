package hu.hwsw.networkdemo.network.rest.model

import com.google.gson.annotations.SerializedName

class AstronautsResponse(
        @SerializedName("message")
        val message:String,
        @SerializedName("number")
        val number: Int,
        @SerializedName("people")
        val astronauts: List<Astronaut>

)