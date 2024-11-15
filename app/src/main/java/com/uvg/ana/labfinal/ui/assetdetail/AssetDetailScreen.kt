package com.uvg.ana.labfinal.ui.assetdetail

import AssetDetailViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ana.labfinal.model.AssetDetail
import com.uvg.ana.labfinal.repository.CryptoRepository
import com.uvg.ana.labfinal.utils.Resource

@Composable
fun AssetDetailScreen(
    repository: CryptoRepository,
    assetId: String
) {
    val viewModel: AssetDetailViewModel = viewModel(
        factory = AssetDetailViewModelFactory(repository, assetId)
    )

    val assetDetailState by viewModel.assetDetail.collectAsState()

    when (val state = assetDetailState) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            state.data?.let { assetDetail ->
                AssetDetailContent(assetDetail = assetDetail)
            }
        }
        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message ?: "Unknown error")
            }
        }
    }
}

@Composable
fun AssetDetailContent(assetDetail: AssetDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "${assetDetail.name} (${assetDetail.symbol})",
            style = MaterialTheme.typography.titleLarge // Usa `titleLarge` para encabezados
        )
        Text(
            text = "Price: $${assetDetail.priceUsd}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Change 24Hr: ${assetDetail.changePercent24Hr}%",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Supply: ${assetDetail.supply}",
            style = MaterialTheme.typography.bodyMedium
        )
        assetDetail.maxSupply?.let {
            Text(
                text = "Max Supply: $it",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = "Market Cap: ${assetDetail.marketCapUsd}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Last Updated: ${assetDetail.updatedAt}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
