package android.forecast.forecast.ui

import android.forecast.R
import android.forecast.forecast.viewholder.WeatherDetailViewHolder
import android.forecast.forecast.viewmodel.WeatherDetailViewModel
import android.forecast.forecast.viewmodel.WeatherViewModelFactory
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var viewHolder: WeatherDetailViewHolder
    private val viewModel by lazy {
        ViewModelProviders.of(this, WeatherViewModelFactory(application, applicationContext))
            .get(WeatherDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewHolder = WeatherDetailViewHolder(findViewById(R.id.rootView), viewModel, lifecycle)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = getString(R.string.search_title)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.fetchWeatherByCityName(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
