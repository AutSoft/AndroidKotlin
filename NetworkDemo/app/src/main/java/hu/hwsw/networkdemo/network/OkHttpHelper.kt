package hu.hwsw.networkdemo.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object OkHttpHelper{

   fun getAstronauts():String{
       val client=OkHttpClient.Builder()
               .connectTimeout(5000,TimeUnit.MILLISECONDS)
               .build()

       val request=Request.Builder()
               .url("http://api.open-notify.org/astros.json")
               .get()
               .build()

       val call=client.newCall(request)
       val response=call.execute()

       val responseStr=response.body()!!.string()
       return  responseStr


   }

}