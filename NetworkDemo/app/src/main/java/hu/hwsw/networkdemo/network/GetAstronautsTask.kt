package hu.hwsw.networkdemo.network

import android.os.AsyncTask
import hu.hwsw.networkdemo.network.rest.RestHelper
import hu.hwsw.networkdemo.network.rest.model.Astronaut

class GetAstronautsTask(val callback:(List<Astronaut>)->Unit): AsyncTask<Void,Void,List<Astronaut>>() {


    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): List<Astronaut> {
        val response=RestHelper.downloadAstronauts()
        return response
     }

    override fun onPostExecute(result: List<Astronaut>?) {
        super.onPostExecute(result)
        callback.invoke(result!!)

    }
}