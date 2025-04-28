package br.com.lucascordeiro.nexly.shared.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun <T : AppNavigation> ComponentActivity.ObserveNavigation(
    navigation: T,
    navController: NavHostController
) {
    LifecycleStartEffect(Unit) {
        fun onEvent(event: AppNavigation.Navigation) {
            when (event) {
                is AppNavigation.Navigation.Route -> navController.navigate(event.route)
                is AppNavigation.Navigation.Back -> navController.popBackStack()
                is AppNavigation.Navigation.Close -> finish()
            }
        }

        navigation.event += ::onEvent

        onStopOrDispose {
            navigation.event -= ::onEvent
        }
    }
}

@Composable
fun <T : AppNavigation> ComponentActivity.NavHost(
    navigation: T,
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit,
) {
    ObserveNavigation(navigation, navController)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = builder,
        modifier = modifier
    )
}

inline fun <reified T : Any> NavGraphBuilder.route(
    noinline content: @Composable AnimatedContentScope.(args: T) -> Unit
) {
    composable<T> { navBackStackEntry ->
        content(navBackStackEntry.toRoute())
    }
}