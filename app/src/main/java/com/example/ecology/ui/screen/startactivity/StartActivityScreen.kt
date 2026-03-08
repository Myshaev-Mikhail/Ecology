package com.example.ecology.ui.screen.startactivity

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecology.EcologyScreen
import com.example.ecology.R
import com.example.ecology.ui.screen.startactivity.intents.StartActivityAction
import com.example.ecology.ui.screen.startactivity.intents.StartActivitySideEffect
import com.example.ecology.ui.uikit.components.AuthButton
import com.example.ecology.ui.uikit.icons.Camera
import com.example.ecology.ui.uikit.icons.Icons
import com.example.ecology.ui.uikit.icons.Leaf
import com.example.ecology.ui.uikit.icons.Resident
import org.koin.androidx.compose.koinViewModel

@SuppressLint("ResourceAsColor")
@Composable
fun StartActivityScreen(
    navController: NavController
    ) {
    val viewModel: StartActivityViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()
    val flags = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.sideEffectEmitter.collect { effect ->
            when(effect) {
                is StartActivitySideEffect.ShowNewReport -> {
                    navController.navigate(EcologyScreen.NewReport.route)
                }
                is StartActivitySideEffect.ShowLogIn -> {
                    navController.navigate(EcologyScreen.LogIn.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.mipmap.image_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.White.copy(alpha = 0.6f),
                            0.5f to Color.White.copy(alpha = 0.9f),
                            1f to Color.White.copy(alpha = 1f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(
                        colorResource(id = R.color.lime),
                        shape = RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Leaf),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "EcoReport",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Сохраняйте свой район зеленым и улицы чистыми.",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            AuthButton(
                icon = rememberVectorPainter(image = Icons.Resident),
                title = "Вход для жильцов",
                subtitle = "Сообщайте о мусоре в вашем районе.",
                background = colorResource(id = R.color.lime),
                onClick = {
                    viewModel.handleUiAction(StartActivityAction.NavigationNewReport)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthButton(
                icon = rememberVectorPainter(image = Icons.Camera),
                title = "вход для репортеров",
                subtitle = "Получите доступ к данным и аналитике.",
                background = colorResource(id = R.color.white_background),
                onClick = {
                    viewModel.handleUiAction(StartActivityAction.NavigationLogIn)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Политика конфиденциальности",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )

                Text(
                    text = "Условия предоставления услуги",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}