package com.noyal.demo.ui.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noyal.demo.ui.component.ComingSoonPlaceHolder
import com.noyal.demo.ui.portfolio.PortfolioScreen

@Composable
fun Navigation() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = Screen.Dashboard
    ) {
        composable<Screen.Dashboard> {
            MainNavigationFlow(rootNavController = rootNavController)
        }
    }
}


@Composable
fun MainNavigationFlow(rootNavController: NavHostController) {
    val mainNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = mainNavController)
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = mainNavController,
            startDestination = Screen.Portfolio,
            enterTransition = {
                scaleIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    initialScale = 0.92f
                ) + fadeIn(animationSpec = tween(220))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(150))
            },
            popEnterTransition = {
                scaleIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    initialScale = 0.92f
                ) + fadeIn(animationSpec = tween(220))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(150))
            }
        ) {
            composable<Screen.Watchlist> { ComingSoonPlaceHolder() }

            composable<Screen.Orders> { ComingSoonPlaceHolder() }

            composable<Screen.Portfolio> {
                PortfolioScreen(hiltViewModel())
            }

            composable<Screen.Funds> { ComingSoonPlaceHolder() }

            composable<Screen.Invest> { ComingSoonPlaceHolder() }

        }
    }
}
