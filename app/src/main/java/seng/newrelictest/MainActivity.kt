package seng.newrelictest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.newrelic.agent.android.NewRelic
import seng.newrelictest.ui.theme.NewRelicTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewRelic.withApplicationToken(
            "TOKEN_HERE"
        ).start(this.applicationContext)

        setContent {
            NewRelicTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val newUserId = "NewUserId-01"
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "New Relic Tracking test")
        Button(onClick = {
            NewRelic.setUserId(newUserId)
        }) {
            Text("Set User ID")
        }
        Button(onClick = {
            val attributes =
                mapOf(
                    "displayMessage" to "New Relic Green Field Test",
                    "GmaUserId" to newUserId
                )
            NewRelic.recordBreadcrumb("TestNewRelic", attributes)
        }) {
            Text("Track breadcrumb")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewRelicTestTheme {
        MainContent()
    }
}
