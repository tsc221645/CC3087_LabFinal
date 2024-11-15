package com.uvg.ana.labfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.labfinal.local.AssetDao
import com.uvg.ana.labfinal.model.Asset
import com.uvg.ana.labfinal.ui.theme.LabFinalTheme
import com.uvg.ana.labfinal.utils.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa la base de datos
        val database = DatabaseProvider.getDatabase(this)
        val dao: AssetDao = database.assetDao()

        // Inserta datos de prueba
        insertSampleData(dao)

        setContent {
            LabFinalTheme {
                val navController = rememberNavController()
                CryptoApp(navController = navController, dao = dao)
            }
        }
    }

    private fun insertSampleData(dao: AssetDao) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertAssets(
                listOf(
                    Asset("bitcoin", "Bitcoin", "BTC", "60000.00", "5.0"),
                    Asset("ethereum", "Ethereum", "ETH", "4000.00", "3.0"),
                    Asset("cardano", "Cardano", "ADA", "2.50", "1.5")
                )
            )
        }
    }
}
