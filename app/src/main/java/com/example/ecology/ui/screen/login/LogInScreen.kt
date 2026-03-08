package com.example.ecology.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecology.EcologyScreen
import com.example.ecology.ui.screen.login.intents.LogInAction
import com.example.ecology.ui.screen.login.intents.LogInSideEffect
import com.example.ecology.ui.screen.mediaaccess.MediaAccessViewModel
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessAction
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessSideEffect
import com.example.ecology.ui.uikit.icons.Leaf
import com.example.ecology.ui.uikit.icons.Visibility
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogInScreen(
    navController: NavController
) {
    val viewModel: LogInViewModel = koinViewModel()
    val uiState by viewModel.uiStateEmitter.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.sideEffectEmitter.collect { effect ->
            when (effect) {
                is LogInSideEffect.ShowNavigationBack -> {
                    navController.popBackStack()
                }
                is LogInSideEffect.ShowNavigationMediaAccess -> {
                    navController.navigate(EcologyScreen.MediaAccess.route)
                }
                is LogInSideEffect.ShowNavigationSignUp -> {
                    navController.navigate(EcologyScreen.SignUp.route)
                }
                is LogInSideEffect.ShowInvalidCredentials -> {
                    Toast.makeText(
                        context,
                        "Неверный email или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
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
                .padding(horizontal = 20.dp),
        ) {
            Spacer(Modifier.height(12.dp))

            Icon(
                modifier = Modifier.clickable {
                    viewModel.handleUiAction(LogInAction.NavigationBack)
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
                color = Color(0xFFB2DFDB),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))

            Spacer(Modifier.weight(1f))

            Column(
                modifier = Modifier.offset(y = -52.dp)
            ) {
                Text("Вход в аккаунт", color = Color.White, fontSize = 18.sp)

                Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = {
                    viewModel.handleUiAction(
                        LogInAction.EmailChange(it)
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
                        LogInAction.PasswordChange(it)
                    )
                },
                label = { Text("Пароль") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.handleUiAction(
                            LogInAction.TogglePasswordVisibility
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

                Button(
                    onClick = {
                        viewModel.handleUiAction(LogInAction.NavigationMediaAccess)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = green),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Войти", color = Color.Black)
                }
            }

            Spacer(Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        viewModel.handleUiAction(LogInAction.NavigationSignUp)
                    },
                text = "Хотите зарегистрироваться?",
                color = Color(0xFFB2DFDB),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}