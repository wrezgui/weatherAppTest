package android.forecast.common.service

import android.forecast.forecast.service.model.Forecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("weather")
    fun getForecastByCityName(@Query("APPID") apiKey: String, @Query("q") cityName: String): Single<Forecast>
}