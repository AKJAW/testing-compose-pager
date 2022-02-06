package com.akjaw.testing.compose.pager

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akjaw.testing.compose.pager.ui.theme.TestingComposePagerTheme

@Composable
fun HomeScreen(
    onDynamicClicked: () -> Unit,
    onStaticClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = onDynamicClicked) {
            Text(text = "Dynamic")
        }
        Button(onClick = onStaticClicked) {
            Text(text = "Static")
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun HomePreview() {
    TestingComposePagerTheme {
        HomeScreen(onDynamicClicked = {}, onStaticClicked = {})
    }
}
