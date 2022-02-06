package com.akjaw.testing.compose.pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.akjaw.testing.compose.pager.ui.theme.TestingComposePagerTheme

sealed class Destination {
    object Home : Destination()
    object DynamicPager : Destination()
    object StaticPager : Destination()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingComposePagerTheme {
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {
    var destination: Destination by remember { mutableStateOf(Destination.Home) }
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (destination) {
                Destination.Home -> {
                    HomeScreen(
                        onDynamicClicked = { destination = Destination.DynamicPager },
                        onStaticClicked = { destination = Destination.StaticPager },
                    )
                }
                Destination.DynamicPager -> { Text(text = "Dynamic") }
                Destination.StaticPager -> { Text(text = "Static") }
            }
        }
    }
}
