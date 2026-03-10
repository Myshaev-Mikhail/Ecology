package com.example.ecology.ui.screen.allreport

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecology.EcologyScreen
import com.example.ecology.R
import com.example.ecology.domain.Report
import com.example.ecology.ui.screen.allreport.intents.AllReportAction
import com.example.ecology.ui.screen.allreport.intents.AllReportSideEffect
import com.example.ecology.ui.uikit.components.GlobalCard
import com.example.ecology.ui.uikit.components.LocationCard
import com.example.ecology.ui.uikit.components.TopBar
import com.example.ecology.ui.uikit.components.TopBarTab
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllReportScreen(
    navController: NavController
) {
    val viewModel: AllReportViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()

    var selectedReport by remember { mutableStateOf<List<Report>?>(null) }

    LaunchedEffect(viewModel) {
        viewModel.sideEffectEmitter.collect { effect ->
            when (effect) {
                is AllReportSideEffect.ShowNavigationNewReport -> {
                    navController.navigate(EcologyScreen.NewReport.route)
                }
                is AllReportSideEffect.ShowNavigationMyReport -> {
                    navController.navigate(EcologyScreen.MyReport.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(top = 52.dp)
            .padding(4.dp)
    ) {
        TopBar(
            isAuthUser = uiState.isAuthUser,
            selectedTab = TopBarTab.ALL_PUBLISHED,
            onNewReportClick = {
                viewModel.handleUiAction(AllReportAction.NavigationNewReport)
            },
            onMyReportsClick = {
                viewModel.handleUiAction(AllReportAction.NavigationMyReport)
            },
            onAllPublishedClick = { }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
                .padding(top = 44.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Все опубликованные отчеты",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Spacer(Modifier.height(12.dp))

            uiState.reports.forEach { group ->
                GlobalCard(
                    district = group.district,
                    street = group.street,
                    house = group.house,
                    description = group.previewReport.description,
                    imageUrl = group.previewReport.photo,
                    reportsCount = group.count,
                    onClick = { selectedReport = group.reports }
                )

                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(30.dp))

            selectedReport?.let { reports ->
                Dialog(onDismissRequest = { selectedReport = null }) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.White)
                            .border(
                                width = 3.dp,
                                color = Color(0xFF9BE3A7),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .heightIn(max = 500.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "Все отчеты по этому адресу",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(Modifier.height(16.dp))

                            reports.forEach { report ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 20.dp)
                                ) {
                                    AsyncImage(
                                        model = report.photo,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(Modifier.height(12.dp))

                                    Text(
                                        text = "${report.district}, улица ${report.street} ${report.house}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )

                                    Spacer(Modifier.height(8.dp))

                                    Text(
                                        text = report.description,
                                        fontSize = 15.sp
                                    )
                                }

                                if (report != reports.last()) {
                                    Spacer(Modifier.height(12.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}