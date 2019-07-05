package android.forecast

import android.app.Application
import com.facebook.stetho.Stetho

class ForecastApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
