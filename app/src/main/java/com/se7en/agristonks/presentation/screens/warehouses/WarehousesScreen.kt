package com.se7en.agristonks.presentation.screens.warehouses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.se7en.agristonks.presentation.app_components.BottomNavBar
import com.se7en.agristonks.uility.Screens

@Composable
fun WarehouseScreen(
    navHostController: NavHostController
) {

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navHostController,
                icon = Screens.WarehouseScreen.selectedIcon,
                label = Screens.WarehouseScreen.label
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues = paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Warehouse", style = MaterialTheme.typography.headlineMedium)

        }

    }

}