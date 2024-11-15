package com.uvg.ana.labfinal

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uvg.ana.labfinal.local.AssetDao
import com.uvg.ana.labfinal.remote.ApiService
import com.uvg.ana.labfinal.remote.KtorClientInstance
import com.uvg.ana.labfinal.repository.CryptoRepositoryImpl
import com.uvg.ana.labfinal.ui.assets.AssetListScreen
import com.uvg.ana.labfinal.ui.assetdetail.AssetDetailScreen

@Composable
fun CryptoApp(navController: NavHostController, dao: AssetDao) {
    // Initialize the ApiService
    val apiService = ApiService(KtorClientInstance.client)

    // Configure the repository with the DAO and ApiService
    val repository = CryptoRepositoryImpl(
        apiService = apiService,
        assetDao = dao
    )

    // Set up navigation
    NavHost(
        navController = navController,
        startDestination = "asset_list"
    ) {
        // Asset list screen
        composable("asset_list") {
            AssetListScreen(
                repository = repository,
                onAssetSelected = { assetId ->
                    navController.navigate("asset_detail/$assetId")
                }
            )
        }
        // Asset detail screen
        composable("asset_detail/{assetId}") { backStackEntry ->
            val assetId = backStackEntry.arguments?.getString("assetId") ?: ""
            AssetDetailScreen(
                repository = repository,
                assetId = assetId
            )
        }
    }
}
