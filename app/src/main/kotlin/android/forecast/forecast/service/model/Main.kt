package android.forecast.forecast.service.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class Main {

    @SerializedName("temp")
    val temperature: BigDecimal = BigDecimal.ZERO

    @SerializedName("pressure")
    val pressure: BigDecimal = BigDecimal.ZERO

    @SerializedName("temp_max")
    val temperatureMax: BigDecimal = BigDecimal.ZERO

    @SerializedName("temp_min")
    val temperatureMin: BigDecimal = BigDecimal.ZERO
}