package com.uvg.ana.labfinal.ui.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ana.labfinal.model.Asset
import com.uvg.ana.labfinal.repository.CryptoRepository

@Composable
fun AssetListScreen(
    repository: CryptoRepository,
    onAssetSelected: (String) -> Unit
) {
    val viewModel: AssetListViewModel = viewModel(
        factory = AssetListViewModelFactory(repository)
    )

    val assets by viewModel.assets.collectAsState()
    val offlineSavedState = viewModel.offlineSavedState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAssets()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Botón "Ver offline"
        Button(
            onClick = { viewModel.saveOffline() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Guardar Offline")
        }

        // Mostrar confirmación de guardado
        if (offlineSavedState.value) {
            Text(
                text = "Datos guardados offline exitosamente",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Lista de assets
        AssetListContent(
            assets = assets,
            onAssetSelected = onAssetSelected
        )
    }
}


@Composable
fun AssetListContent(
    assets: List<Asset>,
    onAssetSelected: (String) -> Unit
) {
    if (assets.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(assets) { asset ->
                AssetItem(
                    asset = asset,
                    onClick = { onAssetSelected(asset.id) }
                )
                Divider()
            }
        }
    }
}

@Composable
fun AssetItem(
    asset: Asset,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "${asset.name} (${asset.symbol})",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Price: $${asset.priceUsd}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = "${asset.changePercent24Hr}%",
            style = MaterialTheme.typography.bodyMedium,
            color = if (asset.changePercent24Hr.startsWith("-")) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    }
}
