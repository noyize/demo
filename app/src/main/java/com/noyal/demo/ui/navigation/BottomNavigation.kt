package com.noyal.demo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material.icons.outlined.CurrencyRupee
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val screen: Screen,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Watchlist, "Watchlist", Icons.AutoMirrored.Outlined.List),
    BottomNavItem(Screen.Orders, "Orders", Icons.Outlined.History),
    BottomNavItem(Screen.Portfolio, "Portfolio", Icons.Outlined.BusinessCenter),
    BottomNavItem(Screen.Funds, "Funds", Icons.Outlined.CurrencyRupee),
    BottomNavItem(Screen.Invest, "Invest", Icons.Outlined.Percent)
)

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(modifier = modifier) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.screen::class.qualifiedName

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.screen) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            )
        }
    }
}
