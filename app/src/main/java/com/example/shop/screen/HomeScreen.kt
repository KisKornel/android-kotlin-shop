package com.example.shop.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.shop.pages.CartPage
import com.example.shop.pages.FavoritePage
import com.example.shop.pages.HomePage
import com.example.shop.pages.ProfilePage
import com.example.shop.service.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    productViewModel: ProductViewModel
) {

    val navItems = listOf(NavItem.Home, NavItem.Favorite, NavItem.Cart, NavItem.Profile)
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, navItem ->
                   NavigationBarItem(
                       selected = index == selectedIndex,
                       onClick = {
                           selectedIndex = index
                       },
                       icon = {
                           Icon(
                               imageVector = navItem.icon,
                               contentDescription = navItem.label,
                           )
                       },
                       label = {
                           Text(text = navItem.label)
                       },
                       colors = NavigationBarItemColors(
                           selectedIconColor = Color.Blue,
                           selectedTextColor = Color.Blue,
                           unselectedIconColor = Color.DarkGray,
                           unselectedTextColor = Color.DarkGray,
                           selectedIndicatorColor = Color.Transparent,
                           disabledIconColor = Color.LightGray,
                           disabledTextColor = Color.LightGray
                       )
                   )
               }
            }
        }
    ) {
        ContentScreen(modifier = Modifier.padding(it), selectedIndex, productViewModel)
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    productViewModel: ProductViewModel
) {
    when(selectedIndex) {
        0 -> HomePage(modifier)
        1 -> FavoritePage(modifier)
        2 -> CartPage(modifier, productViewModel)
        3 -> ProfilePage(modifier)
    }
}

sealed class NavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home     : NavItem("home",     Icons.Default.Home,         "Home")
    object Favorite : NavItem("favorite", Icons.Default.Favorite,     "Favorite")
    object Cart     : NavItem("cart",     Icons.Default.ShoppingCart, "Cart")
    object Profile  : NavItem("profile",  Icons.Default.Person,       "Profile")
}
