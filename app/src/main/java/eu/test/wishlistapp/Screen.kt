package eu.test.wishlistapp

sealed class Screen(val route:String) {
    object HomeScreen:Screen("home_screen")
    object AddScreen: Screen("add_Screen")
}