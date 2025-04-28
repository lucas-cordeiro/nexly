package br.com.lucascordeiro.nexly.shared.navigation

abstract class AppNavigation {
    val event = NavigationEvent<Navigation>()

    sealed class Navigation {
        data class Route(val route: Any) : Navigation()
        object Back : Navigation()
        object Close : Navigation()
    }

    fun navigateTo(route: Any){
        event(Navigation.Route(route))
    }

    fun navigateBack(){
        event(Navigation.Back)
    }

    fun close(){
        event(Navigation.Close)
    }
}