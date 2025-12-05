package com.example.movieapp.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.*

data class NavItem(
    val icon: ImageVector,
    val route: String
)

@Composable
fun FloatingBottomNavigationBar(
    selectedRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val navItems = listOf(
        NavItem(Icons.Default.Home, "home"),
        NavItem(Icons.Default.Search, "search"),
        NavItem(Icons.Default.Favorite, "favorites"),
        NavItem(Icons.Default.Person, "profile")
    )

    @Composable
    fun animatedSize(route: String): Dp {
        return animateDpAsState(
            targetValue = if (selectedRoute == route) 36.dp else 28.dp,
            animationSpec = tween(durationMillis = 250)
        ).value
    }
        Surface(
            modifier = Modifier
                .width(400.dp)
                .height(70.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(50)
                ),
            color = DarkIndigoBlue.copy(alpha = 0.3f),
            shape = RoundedCornerShape(70),
            tonalElevation = 0.dp
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEach { item ->
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.route,
                            tint = if (selectedRoute == item.route) Color.White else Color.Gray,
                            modifier = Modifier
                                .size(animatedSize(item.route))
                                .clickable { onNavigate(item.route) }
                        )
                    }
                }
            }
        }
    }
