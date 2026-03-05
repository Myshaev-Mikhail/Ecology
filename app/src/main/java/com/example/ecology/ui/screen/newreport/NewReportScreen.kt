package com.example.ecology.ui.screen.newreport

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecology.EcologyScreen
import com.example.ecology.R
import com.example.ecology.ui.screen.newreport.intents.NewReportAction
import com.example.ecology.ui.screen.newreport.intents.NewReportSideEffect
import com.example.ecology.ui.uikit.components.FormForAddress
import com.example.ecology.ui.uikit.components.TopBar
import com.example.ecology.ui.uikit.components.TopBarTab
import com.example.ecology.ui.uikit.icons.Camera
import com.example.ecology.ui.uikit.icons.Icons
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewReportScreen(
    navController: NavController
) {
    val viewModel: NewReportViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()

    val context = LocalContext.current

    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenDocument()
        ) { uri ->
            uri?.let {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

                viewModel.handleUiAction(
                    NewReportAction.PhotoSelected(it)
                )
            }
        }

    LaunchedEffect(viewModel) {
        viewModel.sideEffectEmitter.collect { effect ->
            when(effect) {
                is NewReportSideEffect.ShowNavigationMyReport -> {
                    navController.navigate(EcologyScreen.MyReport.route)
                }
                is NewReportSideEffect.ShowNavigationAllReport -> {
                    navController.navigate(EcologyScreen.AllReport.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6F7))
            .padding(top = 52.dp)
            .padding(4.dp)
    ) {
        TopBar(
            isAuthUser = uiState.isAuthUser,
            selectedTab = TopBarTab.NEW_REPORT,
            onNewReportClick = { },
            onMyReportsClick = {
                viewModel.handleUiAction(NewReportAction.NavigationMyReport)
            },
            onAllPublishedClick = {
                viewModel.handleUiAction(NewReportAction.NavigationAllReport)
            }
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
                .padding(top = 44.dp)
        ) {
            Text(
                text = "Данные о месте",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.height(12.dp))

            FormForAddress(
                district = uiState.district.orEmpty(),
                street = uiState.street.orEmpty(),
                house = uiState.house.orEmpty(),
                onDistrictChange = {
                    viewModel.handleUiAction(NewReportAction.DistrictChanged(it))
                },
                onStreetChange = {
                    viewModel.handleUiAction(NewReportAction.StreetChanged(it))
                },
                onHouseChange = {
                    viewModel.handleUiAction(NewReportAction.HouseChanged(it))
                }
            )

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Фото и доказательства",
                    style = MaterialTheme.typography.titleSmall
                )

                Text("1 фото", color = Color.Gray)
            }

            Spacer(Modifier.height(12.dp))

            if (uiState.photo == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFEFF5EF))
                        .clickable { galleryLauncher.launch(arrayOf("image/*")) }
                        .drawBehind {

                            val strokeWidth = 3.dp.toPx()
                            val dashWidth = 14.dp.toPx()
                            val gap = 10.dp.toPx()

                            drawRoundRect(
                                color = Color(0xFF4CAF50),
                                size = size,
                                style = Stroke(
                                    width = strokeWidth,
                                    pathEffect = PathEffect.dashPathEffect(
                                        floatArrayOf(dashWidth, gap),
                                        0f
                                    )
                                ),
                                cornerRadius = CornerRadius(24.dp.toPx())
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Camera),
                            contentDescription = null,
                            tint = Color(0xFF00C853),
                            modifier = Modifier.size(64.dp)
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            text = "Upload or Take a Photo",
                            color = Color(0xFF00C853),
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            text = "JPEG, PNG up to 10MB",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }

            } else {
                Box {
                    AsyncImage(
                        model = uiState.photo,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .clickable { galleryLauncher.launch(arrayOf("image/*")) },
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Описание",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.height(12.dp))

            BasicTextField(
                value = uiState.description,
                onValueChange = {
                    viewModel.handleUiAction(NewReportAction.DescriptionChanged(it))
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    color = colorResource(id = R.color.black)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                minLines = 4,
                maxLines = 8
            )

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF00E600))
                    .clickable {
                        viewModel.handleUiAction(NewReportAction.SubmitClick)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Отправить отчет",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}