package android.forecast.forecast.service.model

import android.forecast.common.helper.Constants
import com.google.gson.annotations.SerializedName

class Forecast {

    @SerializedName("coord")
    val coord: Coordinator? = null

    @SerializedName("weather")
    val weather: List<Weather>? = emptyList()

    @SerializedName("main")
    val main: Main? = null

    @SerializedName("wind")
    val wind: Wind? = null

    @SerializedName("name")
    val cityName: String? = Constants.EMPTY
}
