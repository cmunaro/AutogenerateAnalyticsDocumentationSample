package cmunaro.github.analyticsdocumantationsample.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    state: HomeState,
    onAdd1000Dollars: () -> Unit,
    onSubtract1000Dollars: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Account balance: ${state.accountBalance.value}")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = onAdd1000Dollars) {
                Text(text = "Add $1000")
            }

            Button(onClick = onSubtract1000Dollars) {
                Text(text = "Subtract $1000")
            }
        }
    }
}
