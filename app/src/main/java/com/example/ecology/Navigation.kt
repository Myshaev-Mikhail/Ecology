package com.example.ecology

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecology.ui.screen.allreport.AllReportScreen
import com.example.ecology.ui.screen.login.LogInScreen
import com.example.ecology.ui.screen.mediaaccess.MediaAccessScreen
import com.example.ecology.ui.screen.myreport.MyReportScreen
import com.example.ecology.ui.screen.newreport.NewReportScreen
import com.example.ecology.ui.screen.signup.SignUpScreen
import com.example.ecology.ui.screen.startactivity.StartActivityScreen

sealed class EcologyScreen(val route: String) {
    data object StartActivity : EcologyScreen("start_activity")
    data object NewReport : EcologyScreen("new_report")
    data object MyReport : EcologyScreen("my_report")
    data object SignUp : EcologyScreen("sign_up")
    data object LogIn : EcologyScreen("log_in")
    data object MediaAccess : EcologyScreen("media_access")
    data object AllReport : EcologyScreen("all_report")
}

@Composable
fun NavigationApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = EcologyScreen.StartActivity.route
    ) {
        composable(EcologyScreen.StartActivity.route) {
            StartActivityScreen(
                navController = navController
            )
        }
        composable(EcologyScreen.NewReport.route) {
            NewReportScreen(
                navController = navController
            )
        }
        composable(EcologyScreen.MyReport.route) {
            MyReportScreen(
                navController = navController
            )
        }
        composable(EcologyScreen.SignUp.route) {
            SignUpScreen(
                navController = navController
            )
        }
        composable(EcologyScreen.LogIn.route) {
            LogInScreen(
                navController = navController
            )
        }
        composable(EcologyScreen.MediaAccess.route) {
            MediaAccessScreen(
                navController = navController
            )
        }
        composable(EcologyScreen.AllReport.route) {
            AllReportScreen(
                navController = navController
            )
        }
    }
}