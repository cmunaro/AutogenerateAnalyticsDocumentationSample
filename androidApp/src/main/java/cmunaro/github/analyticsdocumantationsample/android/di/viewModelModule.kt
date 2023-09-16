package cmunaro.github.analyticsdocumantationsample.android.di

import cmunaro.github.analyticsdocumantationsample.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}
