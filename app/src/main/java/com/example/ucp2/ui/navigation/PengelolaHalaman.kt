package com.example.ucp2.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.InsertBrgView
import com.example.ucp2.ui.view.InsertSuplierView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeView(
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsertSplr.route
        ) {
            InsertSuplierView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

    }


}