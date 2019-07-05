package android.forecast.forecast.service.repository

import android.forecast.common.service.ApiClient
import android.forecast.forecast.service.model.Forecast
import io.reactivex.Single

class ForecastRepository(private val apiClient: ApiClient) {

    fun getWeatherForecast(apiKey: String, cityName: String): Single<Forecast> {
        return apiClient.getForecastApi(apiKey, cityName)
    }
}
