package com.se7en.agristonks.presentation.app_components

import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.se7en.agristonks.R
import com.se7en.agristonks.ui.theme.darkBlue
import com.se7en.agristonks.uility.Screens
import com.se7en.agristonks.uility.UserType
import kotlinx.coroutines.delay

@Composable
fun SidebarContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SidebarHeader()
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(0.9f)
                .background(darkBlue)
        )
        SideBarList()
    }

}

@Composable
fun SideBarList() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SideBarItem(
            icon = Icons.Filled.Book,
            text = "Learning"
        )
        SideBarItem(
            icon = Icons.Filled.Dashboard,
            text = "Web Dashboard"
        )
        SideBarItem(
            icon = Icons.Filled.Person,
            text = "Ai chat Assistant"
        )
    }
}

@Composable
fun SideBarItem(
    icon: ImageVector,
    text: String
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

    }

}

@Preview(showBackground = true)
@Composable
fun PrevSideBar() {
    SidebarContent()
}

@Composable
fun SidebarHeader(
    modifier: Modifier? = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)
    ) {

        Image(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth(0.3f),
            painter = painterResource(id = R.drawable.first),
            contentDescription = "profile pic"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Gourav kumar",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "view profile",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }


    }

}

@Composable
fun BottomNavBar(
    navController: NavController,
    icon: ImageVector,
    label: String
) {
    val navigationItems = listOf(
        Screens.HomeScreen,
        Screens.WarehouseScreen,
        Screens.OrdersScreen,
        Screens.ProfileScreen
    )
    var selectedItemIndex by rememberSaveable {

        mutableIntStateOf(0)

    }

    // get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {

        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                // Does any destination in the hierarchy have
                // the same route as the item we're currently rendering in the bottom bar?
                // if true then true else false ( cond == true )
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedItemIndex = index
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                icon = {
                    Icon(
//                        imageVector = if (index == selectedItemIndex) {
//                            item.selectedIcon
//                        } else {
//                            item.unselectedIcon
//                        },
                        imageVector = item.selectedIcon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}


@Composable
fun UserRoleDropdown(
    selectedRole: UserType?,
    onRoleSelected: (UserType) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }
    val roles = listOf(UserType.FARMER, UserType.WAREHOUSE, UserType.SERVICE_PROVIDER)

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedRole?.typeName ?: "",
            onValueChange = { },
            label = {
                Text(
                    text = "Signing up as",
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            },
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onBackground
            ),
            textStyle = TextStyle.Default,
            trailingIcon = {
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Expand dropdown"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            modifier = Modifier.align(Alignment.BottomEnd),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            roles.forEach { role ->
                DropdownMenuItem(
                    onClick = {
                        onRoleSelected(role)
                        expanded.value = false
                    },
                    text = {
                        Text(
                            text = role.typeName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}


@Composable
fun UserTypeSelection(
    selectedType: MutableState<UserType?>,
    onTypeSelected: (UserType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserTypeCard(
            modifier = Modifier.weight(1f),
            userType = UserType.FARMER,
            isSelected = selectedType.value == UserType.FARMER,
            onClick = {
                if (selectedType.value != UserType.FARMER) {
                    selectedType.value = UserType.FARMER
                    onTypeSelected(UserType.FARMER)
                }
            }
        )
        UserTypeCard(
            modifier = Modifier.weight(1f),
            userType = UserType.WAREHOUSE,
            isSelected = selectedType.value == UserType.WAREHOUSE,
            onClick = {
                if (selectedType.value != UserType.WAREHOUSE) {
                    selectedType.value = UserType.WAREHOUSE
                    onTypeSelected(UserType.WAREHOUSE)
                }
            }
        )
        UserTypeCard(
            modifier = Modifier.weight(1f),
            userType = UserType.SERVICE_PROVIDER,
            isSelected = selectedType.value == UserType.SERVICE_PROVIDER,
            onClick = {
                if (selectedType.value != UserType.SERVICE_PROVIDER) {
                    selectedType.value = UserType.SERVICE_PROVIDER
                    onTypeSelected(UserType.SERVICE_PROVIDER)
                }
            }
        )
    }
}

@Composable
private fun UserTypeCard(
    modifier: Modifier? = Modifier,
    userType: UserType,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable(
                onClick = onClick
            )
            .padding(8.dp)
            .defaultMinSize(minHeight = 100.dp),
        elevation = if (isSelected) {
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        } else {
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        },
        border = BorderStroke(
            width = 1.dp,
            color =
            if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
        ),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userType.typeName,
                    modifier = Modifier.padding(8.dp),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color =
                        if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameInputFields(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    placeholder: String,
    label: String,
    text: MutableState<String>
) {


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        value = text.value,
        onValueChange = {
            text.value = it
        },
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        label = { Text(text = label, color = MaterialTheme.colorScheme.onSecondaryContainer) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        textStyle = TextStyle.Default,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInputFields(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    placeholder: String,
    label: String,
    text: MutableState<String>
) {


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        label = { Text(text = label, color = MaterialTheme.colorScheme.onSecondaryContainer) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        textStyle = TextStyle.Default,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    placeholder: String,
    label: String,
    text: MutableState<String>,
    imeNext: Boolean
) {
    var showPassword by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        label = { Text(text = label, color = MaterialTheme.colorScheme.onSecondaryContainer) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        textStyle = TextStyle.Default,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = if (imeNext) ImeAction.Next else ImeAction.Done
        ),
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )

}

@Composable
fun LogInButton(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String
) {

    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewComponents() {
//
////    CardCheckboxRow()
//
//}


//@Preview(showBackground = true)
//@Composable
//fun PreviewButton() {
//
//    LogInButton(onClick = { /*TODO*/ }, text = "Log in", modifier = Modifier)
//
//}