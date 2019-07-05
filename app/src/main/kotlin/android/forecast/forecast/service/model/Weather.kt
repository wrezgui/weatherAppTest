package android.forecast.forecast.service.model

import android.forecast.common.helper.Constants
import com.google.gson.annotations.SerializedName

class Weather {

    @SerializedName("id")
    val id: Long = Constants.NO_ID

    @SerializedName("main")
    val main: String = Constants.EMPTY

    @SerializedName("description")
    val description: String = Constants.EMPTY

    @SerializedName("icon")
    val icon: String = Constants.EMPTY
}