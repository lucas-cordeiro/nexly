package br.com.lucascordeiro.nexly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.lucascordeiro.nexly.feature.details.presentation.DetailsScreen
import br.com.lucascordeiro.nexly.feature.home.presentation.HomeScreen
import br.com.lucascordeiro.nexly.shared.navigation.MainNavigation
import br.com.lucascordeiro.nexly.shared.navigation.NavHost
import br.com.lucascordeiro.nexly.shared.navigation.route
import br.com.lucascordeiro.nexly.shared.navigation.routes.DetailsRoute
import br.com.lucascordeiro.nexly.shared.navigation.routes.HomeRoute
import br.com.lucascordeiro.nexly.shared.ui.theme.UiNexlyTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiNexlyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navigation = MainNavigation,
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        route<HomeRoute> { HomeScreen() }
                        route<DetailsRoute> { DetailsScreen(it) }
                    }
                }
            }
        }
    }
}