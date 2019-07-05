package android.forecast.common.service

import android.forecast.common.helper.Constants
import android.forecast.forecast.service.model.Forecast
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class ApiClient {

    private var api: ForecastApi? = null
    private var retrofit by Delegates.notNull<Retrofit>()
    private var baseUrl: String = Constants.EMPTY

    fun configureRetrofitInstance() {
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(ForecastApi::class.java)
    }

    fun setBaseUrl(url: String) {
        baseUrl = url
    }

    private fun getBaseUrl(): String {
        return baseUrl
    }

    fun getForecastApi(apiKey: String, cityName: String): Single<Forecast> {
        if (api != null) {
            return api!!.getForecastByCityName(apiKey, cityName)
        } else {
            throw NullPointerException("This is an expcetion")
        }
    }
}
