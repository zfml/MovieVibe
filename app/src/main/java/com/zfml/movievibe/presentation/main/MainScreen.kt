package com.zfml.movievibe.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zfml.movievibe.presentation.navigation.AppNavHost
import com.zfml.movievibe.presentation.navigation.Destination
import com.zfml.movievibe.ui.theme.poppinsFontFamily

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val startDestination = Destination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(

        topBar = {

        },
        content = { paddingValues ->
            AppNavHost(
                navController = navController,
                startDestination = Destination.HOME,
                modifier = Modifier.padding(paddingValues)
            )
        },
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    val selected = selectedDestination == index
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                            selectedDestination = index
                        },
                        icon = {
                            if(selected) {
                                Icon(
                                    painter = painterResource(destination.iconActive),
                                    contentDescription = destination.label
                                )
                            }else {
                                Icon(
                                    painter = painterResource(destination.icon),
                                    contentDescription = destination.label
                                )
                            }

                        },
                        label = {
                            if(selected){
                                Text(
                                    text = destination.name,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }else {
                                Text(
                                    text = destination.name,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }

                        }
                    )
                }
            }
        }
    )
}