package hu.autsoft.example.kotlindemos2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Adapter {
    fun addItem() {}
}

class ListActivity : AppCompatActivity() {

    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(...)

        adapter = Adapter()
        // recyclerView.adapter = adapter
    }

    fun addItem() {
        adapter.addItem()
    }

    fun x(message: String?, message2: String?) {
        message ?: return
        message2 ?: return

        println(message.length + message2.length)
    }

}
