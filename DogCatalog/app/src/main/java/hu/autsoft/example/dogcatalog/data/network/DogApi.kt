package hu.autsoft.example.dogcatalog.data.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    class AllBreedsResponse(
            @SerializedName("status")
            val status: String,

            @SerializedName("message")
            val breedsAndSubBreeds: Map<String, List<String>>
    )

    class RandomPictureResponse(
            @SerializedName("status")
            val status: String,

            @SerializedName("message")
            val imageUrl: String
    )

    @GET("breeds/list/all")
    fun getAllBreeds(): Call<AllBreedsResponse>

    @GET("breed/{breedName}/images/random")
    fun getRandomPicture(@Path("breedName") breedName: String): Call<RandomPictureResponse>

}
