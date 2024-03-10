package org.classapp.whatsup

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class WhatsUpNavItemInfo(
    val label:String = "",
    val icon:ImageVector = Icons.Filled.Star,
    val route:String = ""
) {
    fun getAllNavItems() : List<WhatsUpNavItemInfo> {
        return listOf(
            WhatsUpNavItemInfo("Hightlight", Icons.Filled.Star, DestinationScreens.Highlight.route),
            WhatsUpNavItemInfo("Near Me", Icons.Filled.LocationOn, DestinationScreens.NearMe.route),
            WhatsUpNavItemInfo("My Events", Icons.Filled.Face, DestinationScreens.MyEvents.route)
        )
    }
}
