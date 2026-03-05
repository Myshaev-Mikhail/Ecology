package com.example.ecology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecology.ui.screen.allreport.AllReportScreen
import com.example.ecology.ui.screen.myreport.MyReportScreen
import com.example.ecology.ui.screen.mediaaccess.MediaAccessScreen
import com.example.ecology.ui.screen.newreport.NewReportScreen
import com.example.ecology.ui.screen.startactivity.StartActivityScreen
import com.example.ecology.ui.uikit.theme.EcologyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcologyTheme {
                val navController = rememberNavController()
                NavigationApp(
                    navController = navController
                )
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcologyTheme {
        Greeting("Android")
    }
}

sealed class EcologyScreen(val route: String) {
    data object StartActivity : EcologyScreen("start_activity")
    data object NewReport : EcologyScreen("new_report")
    data object MyReport : EcologyScreen("my_report")
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