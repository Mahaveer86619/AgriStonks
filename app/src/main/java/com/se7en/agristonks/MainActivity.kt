package com.se7en.agristonks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.se7en.agristonks.presentation.navigation.NavGraph
import com.se7en.agristonks.presentation.viewmodels.SplashViewModel
import com.se7en.agristonks.ui.theme.AgriStonksTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }

        setContent {
            AgriStonksTheme {

                val startScreen by splashViewModel.startDestination

                val navController = rememberNavController()
                NavGraph(navHostController = navController, startDestination = startScreen)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Retrieve the ViewModel instance using ViewModelProvider
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }
}
