package com.se7en.agristonks.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.se7en.agristonks.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = "Ready to Explore?")
            }
        }
    }
}

@Composable
fun PagerScreen(
    modifier: Modifier = Modifier,
    onBoardingPages: OnBoardingPage
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.6f)
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            painter = painterResource(id = onBoardingPages.image),
            contentDescription = null
        )

        Text(
            text = onBoardingPages.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp,
                    vertical = 6.dp
                ),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Text(
            text = onBoardingPages.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp,
                    vertical = 6.dp
                ),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}


sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnBoardingPage(
        image = R.drawable.first,
        title = "Empowering Farmers, Streamlining Agriculture",
        description = "Welcome to Harvestfy, your one-stop platform for seamless harvest management! We connect farmers with reliable warehouses and service providers, streamline the storage process, and empower you to track your harvest journey every step of the way. Let's get started!"
    )

    data object Second : OnBoardingPage(
        image = R.drawable.second,
        title = "Seamlessly list, store, and track your harvests from field to buyer.",
        description = "Choose your role to access features tailored to your needs. Farmers can find storage solutions, request quotes, and track their harvests. Service providers can showcase their offerings, manage availability, and connect with new clients."
    )

    data object Third : OnBoardingPage(
        image = R.drawable.third,
        title = "Create your account and unlock a world of possibilities.",
        description = "Create your account and tell us about your farm. What crops do you grow? What are your storage needs?"
    )

}