package hu.hwsw.networkdemo.network.rest.model

import com.google.gson.annotations.SerializedName

class Astronaut(
        @SerializedName("name")
        val name:String,
        @SerializedName("craft")
        val craft: String
)