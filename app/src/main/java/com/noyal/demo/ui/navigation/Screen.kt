package com.noyal.demo.ui.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Dashboard : Screen

    @Serializable
    data object Watchlist : Screen

    @Serializable
    data object Orders : Screen

    @Serializable
    data object Portfolio : Screen

    @Serializable
    data object Funds : Screen

    @Serializable
    data object Invest : Screen


}