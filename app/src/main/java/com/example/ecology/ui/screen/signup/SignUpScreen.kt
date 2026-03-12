package com.example.ecology.ui.screen.signup

import android.R.attr.color
import android.util.Log
import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarDefaults.color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.ecology.EcologyScreen
import com.example.ecology.R
import com.example.ecology.ui.screen.signup.intents.SignUpAction
import com.example.ecology.ui.screen.signup.intents.SignUpSideEffect
import com.example.ecology.ui.uikit.icons.Leaf
import com.example.ecology.ui.uikit.icons.Visibility
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    navController: NavController
) {
    val viewModel: SignUpViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()

    val context = LocalContext.current
    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS
        .matcher(uiState.email)
        .matches()

    LaunchedEffect(viewModel) {
        viewModel.sideEffectEmitter.collect { effect ->
            when (effect) {
                is SignUpSideEffect.ShowNavigationBack -> {
                    navController.popBackStack()
                }

                is SignUpSideEffect.ShowNavigationNewReport -> {
                    navController.navigate(EcologyScreen.NewReport.route)
                }

                is SignUpSideEffect.ShowNavigationLogIn -> {
                    navController.navigate(EcologyScreen.LogIn.route)
                }

                is SignUpSideEffect.ShowToast -> {
                    Toast.makeText(
                        context,
                        effect.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    val green = colorResource(id = R.color.lime)
    val bgGradient = Brush.verticalGradient(
        colors = listOf(colorResource(id = R.color.dark_green), colorResource(id = R.color.black_green))
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
                .imePadding()
                .padding(horizontal = 20.dp),
        ) {
            Spacer(Modifier.height(12.dp))

            Icon(
                modifier = Modifier.clickable {
                    viewModel.handleUiAction(SignUpAction.NavigationBack)
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                "Доступ для медиа",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(24.dp))

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

            Spacer(Modifier.height(12.dp))

            Text(
                "Профессиональный портал для экологических репортажей и анализа данных.",
                color = colorResource(id = R.color.add_text),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))

            Spacer(Modifier.weight(1f))

            Column {
                Text("Регистрация", color = Color.White, fontSize = 18.sp)

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.nickname,
                    onValueChange = {
                        viewModel.handleUiAction(
                            SignUpAction.NicknameChange(it)
                        )
                    },
                    label = { Text("Имя") },
                    placeholder = { Text("Михаил") },
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
                    value = uiState.email,
                    onValueChange = {
                        viewModel.handleUiAction(
                            SignUpAction.EmailChange(it)
                        )
                    },
                    label = { Text("Рабочая почта") },
                    placeholder = { Text("name@organization.com") },
                    singleLine = true,
                    isError = uiState.email.isNotEmpty() && !isEmailValid,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = green.copy(alpha = 0.5f),
                        focusedBorderColor = green,
                        errorBorderColor = Color.Red,
                        cursorColor = green,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Gray,
                        errorLabelColor = Color.Red,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        errorTextColor = Color.White
                    ),
                    supportingText = {
                        if (uiState.email.isNotEmpty() && !isEmailValid) {
                            Text("Введите корректный email")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(14.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = {
                        viewModel.handleUiAction(
                            SignUpAction.PasswordChange(it)
                        )
                    },
                    label = { Text("Пароль") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.handleUiAction(
                                SignUpAction.TogglePasswordVisibility
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

                Button(
                    onClick = {
                        Log.d("SignUp", "Кнопка нажата")
                        viewModel.handleUiAction(SignUpAction.OnPopup)
                    },
                    enabled = isEmailValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = green,
                        disabledContainerColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Зарегистрироваться", color = Color.Black)
                }
            }

            Spacer(Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        viewModel.handleUiAction(SignUpAction.NavigationLogIn)
                    },
                text = "У вас уже есть аккаунт?",
                color = colorResource(id = R.color.add_text),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))
        }
        if (uiState.isPopup) {
            Dialog(onDismissRequest = {
                viewModel.handleUiAction(SignUpAction.ClosePopup)
            }) {
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
                            color = colorResource(id = R.color.add_text),
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
                                viewModel.handleUiAction(SignUpAction.Subscription)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = green),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Оформить подписку",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        Text(
                            "24,99 $/мес. для организаций. Отменить можно в любое время. Оформляя подписку, вы соглашаетесь с условиями использования медиа.",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}