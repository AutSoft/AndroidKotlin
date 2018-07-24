package hu.hwsw.networkdemo.network.rest

import hu.hwsw.networkdemo.network.rest.model.Astronaut
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestHelper{

    fun downloadAstronauts(): List<Astronaut> {
        val retrofit=Retrofit.Builder()
                .baseUrl("http://api.open-notify.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val issApi=retrofit.create(ISSApi::class.java)

        val astoronautsResponse=issApi.getAstronauts().execute()
        return astoronautsResponse.body()!!.astronauts


    }

}