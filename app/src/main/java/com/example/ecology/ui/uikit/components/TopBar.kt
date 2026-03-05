package com.example.ecology.ui.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun TopBar(
    isAuthUser: Boolean,
    selectedTab: TopBarTab,
    onNewReportClick: () -> Unit,
    onMyReportsClick: () -> Unit,
    onAllPublishedClick: () -> Unit,

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE9ECEF))
            .padding(4.dp)
            .zIndex(1F)
    ) {
        TopBarItem(
            modifier = Modifier.weight(1f),
            title = "Новый отчет",
            selected = selectedTab == TopBarTab.NEW_REPORT,
            onClick = onNewReportClick
        )

        TopBarItem(
            modifier = Modifier.weight(1f),
            title = "Мои отчеты",
            selected = selectedTab == TopBarTab.MY_REPORTS,
            onClick = onMyReportsClick
        )

        if (isAuthUser) {
            TopBarItem(
                modifier = Modifier.weight(1f),
                title = "Все отчеты",
                selected = selectedTab == TopBarTab.ALL_PUBLISHED,
                onClick = onAllPublishedClick
            )
        }
    }
}

@Composable
private fun TopBarItem(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color.White else Color.Transparent)
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) Color.Black else Color.Gray
        )
    }
}

enum class TopBarTab {
    NEW_REPORT,
    MY_REPORTS,
    ALL_PUBLISHED
}