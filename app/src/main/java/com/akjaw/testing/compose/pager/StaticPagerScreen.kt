package com.akjaw.testing.compose.pager

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.akjaw.testing.compose.pager.ui.theme.TestingComposePagerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

object TestTagsStaticPagerScreen {

    private const val page = "static-pager"
    const val tabRow = "static-pager-tab-row"

    fun getPageTag(page: StaticPagerScreenPage) = "$page-${page.ordinal}"
}

enum class StaticPagerScreenPage {
    Summary,
    Info,
    Details,
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StaticPagerScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages = remember {
        listOf(
            StaticPagerScreenPage.Summary,
            StaticPagerScreenPage.Info,
            StaticPagerScreenPage.Details,
        )
    }
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            modifier = Modifier.testTag(TestTagsStaticPagerScreen.tabRow),
        ) {
            pages.forEachIndexed { index, page ->
                Tab(
                    text = { Text(page.name) },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.scrollToPage(index) } },
                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { index ->
            val page = pages[index]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TestTagsStaticPagerScreen.getPageTag(page))
            ) {
                when (page) {
                    StaticPagerScreenPage.Summary -> SummaryContent(
                        onInformationClicked = {
                            scope.launch { pagerState.animateScrollToPage(1) }
                        },
                        onDetailsClicked = {
                            scope.launch { pagerState.animateScrollToPage(2) }
                        },
                    )
                    StaticPagerScreenPage.Info -> InformationContent()
                    StaticPagerScreenPage.Details -> DetailsContent()
                }
            }
        }
    }
}

@Composable
private fun SummaryContent(
    onInformationClicked: () -> Unit,
    onDetailsClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "The Summary page", style = TextStyle(fontSize = 22.sp))
        Button(onClick = onInformationClicked) {
            Text(text = "Go to info")
        }
        Button(onClick = onDetailsClicked) {
            Text(text = "Go to details")
        }
    }

}

@Composable
private fun InformationContent() {
    Text(text = "The Information page", style = TextStyle(fontSize = 22.sp))
}

@Composable
private fun DetailsContent() {
    Text(text = "The Details page", style = TextStyle(fontSize = 22.sp))
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun StaticPagerScreenPreview() {
    TestingComposePagerTheme {
        StaticPagerScreen()
    }
}