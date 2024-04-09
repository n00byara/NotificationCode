package ru.n00byara.notificationcode.ui.components.bottomnavigationbar

import androidx.navigation.NavController

data class BottomNavBarModel(
    val navController: NavController,
    val prefName: String,
    val selected: Int,
    val setSelected: (String, Int) -> Unit
)