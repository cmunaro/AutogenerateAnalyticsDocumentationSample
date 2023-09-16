package cmunaro.github.analyticsdocumantationsample.android

import android.app.Application
import cmunaro.github.analyticsdocumantationsample.android.di.viewModelModule
import cmunaro.github.analyticsdocumantationsample.di.sharedModule
import org.koin.core.context.GlobalContext.startKoin

class AnalyticsDocumentationSampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(sharedModule, viewModelModule)
        }
    }
}
