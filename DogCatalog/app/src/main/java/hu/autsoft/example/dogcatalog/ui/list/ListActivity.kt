package hu.autsoft.example.dogcatalog.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import hu.autsoft.example.dogcatalog.R
import hu.autsoft.example.dogcatalog.ui.details.DetailActivity
import kotlinx.android.synthetic.main.activity_list.*
import timber.log.Timber

class ListActivity : AppCompatActivity(), DogAdapter.Listener {

    lateinit var adapter: DogAdapter

    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        adapter = DogAdapter()
        adapter.listener = this
        recyclerView.adapter = adapter

        val factory = ListViewModelFactory(applicationContext)
        viewModel = ViewModelProviders.of(this, factory).get(ListViewModel::class.java)

        viewModel.init()
        viewModel.getDogs().observe(this, Observer { dogs ->
            Timber.d("Activity got dog list")
            if (dogs != null) {
                adapter.setDogs(dogs)
            }

            swipeRefreshlayout.isRefreshing = false
        })

        swipeRefreshlayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onDogSelected(breed: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_DOG_BREED, breed)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_db -> {
                viewModel.clearDb()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
