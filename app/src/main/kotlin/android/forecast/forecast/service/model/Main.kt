package android.forecast.forecast.service.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class Main {

    @SerializedName("temps")
    val temps: BigDecimal = BigDecimal.ZERO

    @SerializedName("pressure")
    val pressure: BigDecimal = BigDecimal.ZERO

    @SerializedName("humidity")
    val humidity: BigDecimal = BigDecimal.ZERO
}