package com.uvg.ana.labfinal.ui.assetdetail

import AssetDetailViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvg.ana.labfinal.repository.CryptoRepository

class AssetDetailViewModelFactory(
    private val repository: CryptoRepository,
    private val assetId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssetDetailViewModel::class.java)) {
            return AssetDetailViewModel(repository, assetId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
