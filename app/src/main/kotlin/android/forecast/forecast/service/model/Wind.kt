package android.forecast.forecast.service.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class Wind {

    @SerializedName("speed")
    val speed: BigDecimal = BigDecimal.ZERO

    @SerializedName("deg")
    val deg: BigDecimal = BigDecimal.ZERO

}