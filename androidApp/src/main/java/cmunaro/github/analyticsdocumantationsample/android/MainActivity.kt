package cmunaro.github.analyticsdocumantationsample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cmunaro.github.analyticsdocumantationsample.android.home.HomeScreen
import cmunaro.github.analyticsdocumantationsample.android.home.HomeViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: HomeViewModel by inject()
            val state by viewModel.state.collectAsState()

            MyApplicationTheme {
                HomeScreen(
                    state = state,
                    onAdd1000Dollars = viewModel::onAdd1000Dollars,
                    onSubtract1000Dollars = viewModel::onSubtract1000Dollars
                )
            }
        }
    }
}
