package com.example.ecology.ui.screen.myreport

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
import com.example.ecology.ui.screen.myreport.intents.MyReportAction
import com.example.ecology.ui.screen.myreport.intents.MyReportSideEffect
import com.example.ecology.ui.uikit.components.LocationCard
import com.example.ecology.ui.uikit.components.TopBar
import com.example.ecology.ui.uikit.components.TopBarTab
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyReportScreen(
    navController: NavController
) {
    val viewModel: MyReportViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()

    var selectedReport by remember { mutableStateOf<Report?>(null) }

    LaunchedEffect(viewModel) {
        viewModel.sideEffectEmitter.collect { effect ->
            when (effect) {
                is MyReportSideEffect.ShowNavigationNewReport -> {
                    navController.navigate(EcologyScreen.NewReport.route)
                }
                is MyReportSideEffect.ShowNavigationAllReport -> {
                    navController.navigate(EcologyScreen.AllReport.route)
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
            selectedTab = TopBarTab.MY_REPORTS,
            onNewReportClick = {
                viewModel.handleUiAction(MyReportAction.NavigationNewReport)
            },
            onMyReportsClick = { },
            onAllPublishedClick = {
                viewModel.handleUiAction(MyReportAction.NavigationAllReport)
            }
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
                    text = if(uiState.reports.isEmpty()) {
                        "Создайте свой первый отчет, для чистоты нашего города"
                    } else {
                        "Мои отчеты"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Spacer(Modifier.height(12.dp))

            uiState.reports.forEach { report ->
                LocationCard(
                    district = report.district,
                    street = report.street,
                    house = report.house,
                    description = report.description,
                    imageUrl = report.photo,
                    onClick = { selectedReport = report }
                )

                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(30.dp))

            selectedReport?.let { report ->
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
                        Column {
                            AsyncImage(
                                model = report.photo,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(Modifier.height(16.dp))

                            Text(
                                text = "${report.district}, улица ${report.street} ${report.house}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(Modifier.height(12.dp))

                            Text(
                                modifier = Modifier
                                    .heightIn(max = 350.dp)
                                    .verticalScroll(rememberScrollState()),
                                text = report.description,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}