package hu.hwsw.networkdemo.network.rest

import hu.hwsw.networkdemo.network.rest.model.AstronautsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ISSApi{

    @GET("astros.json")
    fun getAstronauts(): Call<AstronautsResponse>
}