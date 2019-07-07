package android.forecast.forecast.viewholder

import android.forecast.R
import android.forecast.common.helper.GlideApp
import android.forecast.common.helper.ScreenUiData
import android.forecast.common.helper.State
import android.forecast.forecast.service.model.Forecast
import android.forecast.forecast.viewmodel.WeatherDetailViewModel
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.view.*
import java.math.BigDecimal
import java.util.*

class WeatherDetailViewHolder(
    private val rootView: View, viewModel: WeatherDetailViewModel,
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
            ).show()
            State.SUCCESS -> {
                rootView.city_name.text = screenUiData.data.cityName
                rootView.today_date.text = Calendar.getInstance().time.toString()
                rootView.weather_temperature.text = rootView.context.getString(
                    R.string.weather_temperature,
                    screenUiData.data.main!!.temperature.divide(BigDecimal(10)).toInt()
                )
                rootView.temperature_max_min.text = rootView.context.getString(
                    R.string.temperature_max_min,
                    screenUiData.data.main.temperatureMax.divide(BigDecimal(10)).toInt(),
                    screenUiData.data.main.temperatureMin.divide(BigDecimal(10)).toInt()
                )
                rootView.main_pressure.text = screenUiData.data.main.pressure.toInt().toString()
                rootView.wind_speed.text = screenUiData.data.wind!!.speed.toInt().toString()

                rootView.weather_description.text = screenUiData.data.weather!!.first().description

                GlideApp.with(rootView.context)
                    .asBitmap()
                    .load(rootView.context.getString(R.string.base_url_img, screenUiData.data.weather.first().icon))
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) {
                            //Nothing to do
                        }

                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            rootView.weather_temperature.setCompoundDrawablesWithIntrinsicBounds(
                                BitmapDrawable(
                                    rootView.context.resources,
                                    resource
                                ), null, null, null
                            )
                        }
                    })

                updateBackground(screenUiData.data.weather.first().id.toString())
            }
        }
    }

    /**
     * Show Background for the weather condition
     * Images are statically added to drawable folder
     * We can have 7 condition weather starting from 2 to 8
     */
    private fun updateBackground(weatherId: String) {
        when {
            weatherId.startsWith("2") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.thunderstorm,
                    null
                )
            )
            weatherId.startsWith("3") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.drizzle,
                    null
                )
            )
            weatherId.startsWith("4") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.rain,
                    null
                )
            )
            weatherId.startsWith("5") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.snow,
                    null
                )
            )
            weatherId.startsWith("6") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.atmosphere,
                    null
                )
            )
            weatherId.startsWith("7") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.clear,
                    null
                )
            )
            weatherId.startsWith("8") -> rootView.weather_condition.setImageDrawable(
                rootView.resources.getDrawable(
                    R.drawable.clouds,
                    null
                )
            )
        }
    }

}
