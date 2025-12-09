package com.example.movieapp.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.*

enum class NavigationTab {
    HOME, SEARCH, FAVORITES, PROFILE
}



@Composable
fun BottomNavigationBar(
    selectedTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit
) {
    NavigationBar(

        modifier = Modifier
            .height(90.dp)
            .background(Basecolor)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp, bottomStart = 25.dp, bottomEnd = 25.dp)),
        containerColor = Darkblue
    ) {

        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            selected = selectedTab == NavigationTab.SEARCH,
            onClick = { onTabSelected(NavigationTab.SEARCH) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            selected = selectedTab == NavigationTab.HOME,
            onClick = { onTabSelected(NavigationTab.HOME) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            selected = selectedTab == NavigationTab.FAVORITES,
            onClick = { onTabSelected(NavigationTab.FAVORITES) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            selected = selectedTab == NavigationTab.PROFILE,
            onClick = { onTabSelected(NavigationTab.PROFILE) }
        )
    }
}
