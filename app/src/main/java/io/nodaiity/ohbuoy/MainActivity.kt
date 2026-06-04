package io.nodaiity.ohbuoy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import io.nodaiity.ohbuoy.core.render.TraceRenderer
import io.nodaiity.ohbuoy.core.scanner.ContinuityScanner
import io.nodaiity.ohbuoy.core.scanner.ScannerMode
import io.nodaiity.ohbuoy.ui.theme.OhbuoyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val packets = ContinuityScanner().run(ScannerMode.STARTUP)
        println(TraceRenderer().render(packets))
        setContent {
            OhbuoyTheme {
                Text(text = "ohbuoy v0")
            }
        }
    }
}
