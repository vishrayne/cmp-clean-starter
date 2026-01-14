package com.yourcompany.yourapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.yourapp.AndroidPlatform
import com.yourcompany.yourapp.App
import com.yourcompany.yourapp.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val component = (application as YourNameApplication)
            .component

        setContent {
            App(component.greeting)
        }
    }
}

@Preview
@Composable
private fun AppAndroidPreview() {
    val greeting = Greeting(AndroidPlatform())
    App(greeting)
}
