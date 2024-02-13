package com.se7en.agristonks.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.se7en.agristonks.presentation.Test.TestPage
import com.se7en.agristonks.presentation.screens.home.HomeScreen
import com.se7en.agristonks.presentation.screens.login_signin.SignInScreen
import com.se7en.agristonks.presentation.screens.login_signin.SignupScreen
import com.se7en.agristonks.presentation.screens.onboarding.OnBoardingScreen
import com.se7en.agristonks.presentation.screens.orders.OrdersScreen
import com.se7en.agristonks.presentation.screens.profile.ProfileScreen
import com.se7en.agristonks.presentation.screens.warehouses.WarehouseScreen
import com.se7en.agristonks.uility.Screens

@Composable
fun NavGraph(
    startDestination: String = Screens.OnBoardingScreen.route,
    navHostController: NavHostController
) {


    NavHost(navController = navHostController, startDestination = startDestination) {

        composable(route = Screens.OnBoardingScreen.route) {
            OnBoardingScreen(navHostController = navHostController)
        }
        composable(route = Screens.LoginScreen.route) {
            SignInScreen(navHostController = navHostController)
        }
        composable(route = Screens.SignupScreen.route) {
            SignupScreen(navHostController = navHostController)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(route = Screens.WarehouseScreen.route) {
            WarehouseScreen(navHostController = navHostController)
        }
        composable(route = Screens.OrdersScreen.route) {
            OrdersScreen(navHostController = navHostController)
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(navHostController = navHostController)
        }
        composable(route = Screens.TestScreen.route) {
            TestPage()
        }

    }

}