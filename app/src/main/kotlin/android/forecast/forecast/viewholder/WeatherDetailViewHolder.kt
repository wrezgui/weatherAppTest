package android.forecast.forecast.viewholder

import android.forecast.R
import android.forecast.common.helper.ScreenUiData
import android.forecast.common.helper.State
import android.forecast.forecast.service.model.Forecast
import android.forecast.forecast.viewmodel.WeatherDetailViewModel
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.view.*

class WeatherDetailViewHolder(
    private val rootView: View, private val viewModel: WeatherDetailViewModel,
    private val lifecycle: Lifecycle
) : DefaultLifecycleObserver {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    init {
        lifecycle.addObserver(this)

        compositeDisposable.add(
            viewModel.dataSource.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(::updateUiData)
        )
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycle.removeObserver(this)
        compositeDisposable.clear()
    }

    private fun updateUiData(screenUiData: ScreenUiData<Forecast>) {
        when (screenUiData.state) {
            State.ERROR -> Snackbar.make(
                rootView,
                rootView.context.getString(R.string.error_retreive_data),
                Snackbar.LENGTH_LONG
            )
            State.SUCCESS -> {
                rootView.city_name.text = screenUiData.data.cityName
                rootView.weather_description.text = screenUiData.data.weather!!.first().description
                rootView.main_pressure.text = screenUiData.data.main!!.pressure.toString()
                rootView.wind_speed.text = screenUiData.data.wind!!.speed.toString()
            }
        }
    }

}
