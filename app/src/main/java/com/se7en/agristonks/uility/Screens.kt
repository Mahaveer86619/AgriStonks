package com.se7en.agristonks.uility

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
) {

    data object TestScreen: Screens(
        route = "test_screen",
        label = "null",
        selectedIcon = Icons.Filled.Close,
        unselectedIcon = Icons.Outlined.Close
    )

    data object OnBoardingScreen: Screens(
        route = "onboarding_screen",
        label = "null",
        selectedIcon = Icons.Filled.Close,
        unselectedIcon = Icons.Outlined.Close
    )

    data object LoginScreen : Screens(
        route = "login_screen",
        label = "null",
        selectedIcon = Icons.Filled.Close,
        unselectedIcon = Icons.Outlined.Close
    )

    data object SignupScreen : Screens(
        route = "signup_screen",
        label = "null",
        selectedIcon = Icons.Filled.Close,
        unselectedIcon = Icons.Outlined.Close
    )

    data object HomeScreen : Screens(
        route = "home_screen",
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object WarehouseScreen : Screens(
        route = "warehouse_screen",
        label = "Warehouses",
        selectedIcon = Icons.Filled.Apartment,
        unselectedIcon = Icons.Outlined.Apartment
    )

    data object OrdersScreen : Screens(
        route = "orders_screen",
        label = "Orders",
        selectedIcon = Icons.Filled.Receipt,
        unselectedIcon = Icons.Outlined.Receipt
    )

    data object ProfileScreen : Screens(
        route = "profile_screen",
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

}
