package android.forecast.forecast.ui

import android.forecast.R
import android.forecast.forecast.viewholder.WeatherDetailViewHolder
import android.forecast.forecast.viewmodel.WeatherDetailViewModel
import android.forecast.forecast.viewmodel.WeatherViewModelFactory
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
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
        return true
    }
}
