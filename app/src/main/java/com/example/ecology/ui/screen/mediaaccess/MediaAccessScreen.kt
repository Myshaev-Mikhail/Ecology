package com.example.ecology.ui.screen.mediaaccess

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.ecology.EcologyScreen
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessAction
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessSideEffect
import com.example.ecology.ui.uikit.icons.Leaf
import com.example.ecology.ui.uikit.icons.Visibility

@Composable
fun MediaAccessScreen(
    navController: NavController
) {
    val viewModel: MediaAccessViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.sideEffectEmitter.collect { effect ->
            when (effect) {
                is MediaAccessSideEffect.ShowNavigationBack -> {
                    navController.popBackStack()
                }
                is MediaAccessSideEffect.ShowSubscription -> {

                }
                is MediaAccessSideEffect.ShowNewReport -> {
                    navController.navigate(EcologyScreen.NewReport.route)
                }
            }
        }
    }

    val green = Color(0xFF00C853)
    val bgGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF021B0F), Color(0xFF06351F))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
            .systemBarsPadding()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(Modifier.height(12.dp))

            Icon(
                modifier = Modifier
                    .clickable {
                        viewModel.handleUiAction(MediaAccessAction.NavigationBack)
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Доступ для медиа",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(28.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = rememberVectorPainter(image = com.example.ecology.ui.uikit.icons.Icons.Leaf),
                    contentDescription = null,
                    tint = green
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "EcoWatch Media",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                "Профессиональный портал для экологических репортажей и анализа данных.",
                color = Color(0xFFB2DFDB),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))

            Text("Вход в аккаунт", color = Color.White, fontSize = 18.sp)

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = {
                    viewModel.handleUiAction(
                        MediaAccessAction.EmailChange(it)
                    )
                },
                label = { Text("Рабочая почта") },
                placeholder = { Text("name@organization.com") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = green.copy(alpha = 0.5f),
                    focusedBorderColor = green,
                    cursorColor = green,
                    focusedLabelColor = green,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(14.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    viewModel.handleUiAction(
                        MediaAccessAction.PasswordChange(it)
                    )
                },
                label = { Text("Пароль") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.handleUiAction(
                            MediaAccessAction.TogglePasswordVisibility
                        )
                    }) {
                        Icon(
                            painter = rememberVectorPainter(image = com.example.ecology.ui.uikit.icons.Icons.Visibility),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = green.copy(alpha = 0.5f),
                    focusedBorderColor = green,
                    cursorColor = green,
                    focusedLabelColor = green,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Забыли пароль?",
                color = green,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        viewModel.handleUiAction(MediaAccessAction.NavigationNewReport)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = green),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Войти", color = Color.Black)
                }
            }

            Spacer(Modifier.height(28.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0A2E1A)
                ),
                border = BorderStroke(1.dp, green.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp)) {

                    Text(
                        "ПРЕМИУМ МЕДИА ЛИЦЕНЗИЯ",
                        color = green,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "Получите полный доступ к данным",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Подтвердите статус медиа-специалиста, чтобы получить доступ к тепловым картам в реальном времени, изображениям высокого разрешения и экспортируемым CSV-отчётам.",
                        color = Color(0xFFB2DFDB),
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.height(16.dp))

                    val features = listOf(
                        "Оповещения в реальном времени о зонах с высокой концентрацией мусора",
                        "Скачивание оригинальных фото-доказательств в 4K",
                        "Анализ исторических трендов и доступ к API",
                        "Прямой контакт с проверенными представителями сообщества"
                    )

                    features.forEach {
                        Row(Modifier.padding(vertical = 4.dp)) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = green,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(it, color = Color.White, fontSize = 14.sp)
                        }
                    }

                    Spacer(Modifier.height(18.dp))

                    Button(
                        onClick = {
                            viewModel.handleUiAction(MediaAccessAction.Subscription)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = green),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            "Оформить подписку и продолжить",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Text(
                        "24,99 $/мес. для организаций. Отменить можно в любое время. Оформляя подписку, вы соглашаетесь с условиями использования медиа.",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}