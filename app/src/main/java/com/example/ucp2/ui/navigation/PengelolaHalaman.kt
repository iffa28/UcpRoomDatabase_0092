package com.example.ucp2.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.DetailBrgView
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.InsertBrgView
import com.example.ucp2.ui.view.InsertSuplierView
import com.example.ucp2.ui.view.ListBarangView
import com.example.ucp2.ui.view.ListSuplierView
import com.example.ucp2.ui.view.UpdateBrgView

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
                onTambahSplrClick = {
                    navController.navigate(DestinasiInsertSplr.route)
                },
                onDaftarSuplierClick = {
                    navController.navigate(DestinasiListSplr.route)
                },
                onTambahBrgClick ={
                    navController.navigate(DestinasiInsertBrg.route)
                } ,
                onDaftarBrgClick = {
                    navController.navigate(DestinasiListBrg.route)
                },
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

        composable(
           route = DestinasiListSplr.route
        ) {
            ListSuplierView(
                onBack = {
                    navController.popBackStack()
                } ,
                modifier = modifier,
            )

        }

        composable(
            route = DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiListBrg.route
        ) {
            ListBarangView(
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailBrg.route}/$id")
                },
                onBack = {
                    navController.popBackStack()
                } ,
                modifier = modifier,
            )

        }

        composable(
            DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailBrg.ID)
            id?.let { id ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$id")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
        }

        composable(
            DestinasiUpdateBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBrg.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateBrgView(
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