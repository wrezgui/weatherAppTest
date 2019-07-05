package android.forecast.forecast.viewmodel


import android.app.Application
import android.content.Context
import android.forecast.R
import android.forecast.common.helper.Constants.Companion.DEFAULT_CITY
import android.forecast.common.helper.ScreenUiData
import android.forecast.common.helper.State
import android.forecast.common.service.ApiClient
import android.forecast.forecast.service.model.Forecast
import android.forecast.forecast.service.repository.ForecastRepository
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers

/**
 * This ViewModel is used to prepare Data for weather screen.
 *
 */
class WeatherDetailViewModel(application: Application, private val context: Context) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    val dataSource: BehaviorProcessor<ScreenUiData<Forecast>> = BehaviorProcessor.createDefault(
        ScreenUiData(state = State.LOADING, data = Forecast())
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        apiClient.setBaseUrl(context.getString(R.string.base_url))
        apiClient.configureRetrofitInstance()
        fetchWeatherByCityName(DEFAULT_CITY)
    }

    /**
     *  fetch the weather details by given city name.
     */
    fun fetchWeatherByCityName(cityName: String) {
        dataSource.onNext(dataSource.value!!.copy(State.LOADING))
        compositeDisposable.add(
            ForecastRepository(apiClient).getWeatherForecast(context.getString(R.string.api_key), cityName).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribeOn(Schedulers.io()).subscribe({ forecast ->
                dataSource.onNext(ScreenUiData(state = State.SUCCESS, data = forecast))
            }, { error ->
                dataSource.onNext(dataSource.value!!.copy(State.ERROR))
                Log.e(TAG, "Failed to fetch weather", error)
            })
        )
    }

    companion object {
        private const val TAG = "weatherViewModel"
    }

}