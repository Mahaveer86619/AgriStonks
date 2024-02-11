package com.se7en.agristonks.presentation.screens.login_signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.se7en.agristonks.presentation.app_components.EmailInputFields
import com.se7en.agristonks.presentation.app_components.LogInButton
import com.se7en.agristonks.presentation.app_components.PasswordInputField
import com.se7en.agristonks.presentation.app_components.UsernameInputFields
import com.se7en.agristonks.uility.Screens

@Composable
fun SignInScreen(
    navHostController: NavHostController,
//     onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {

        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Create An Account",
                fontSize = 28.sp,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Enter your credentials",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .weight(2f)
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            UsernameInputFields(
                leadingIcon = Icons.Outlined.Person,
                placeholder = "Enter your username",
                label = "Name"
            )
            EmailInputFields(
                leadingIcon = Icons.Outlined.Mail,
                placeholder = "Enter your email",
                label = "Email"
            )
            PasswordInputField(
                leadingIcon = Icons.Outlined.Lock,
                placeholder = "Enter your password",
                label = "Password"
            )
            PasswordInputField(
                leadingIcon = Icons.Outlined.Lock,
                placeholder = "Re-enter your password",
                label = "Password"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = "Forgot Password",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.secondary
                )

            }


        }

        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogInButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                onClick = {
                    //onBoardingViewModel.saveOnBoardingState(true) // completed = true
                },
                text = "Sign up"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navHostController.popBackStack()
                        navHostController.navigate(Screens.LoginScreen.route)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "click here",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
        Spacer(modifier = Modifier.weight(0.25f))
    }

}