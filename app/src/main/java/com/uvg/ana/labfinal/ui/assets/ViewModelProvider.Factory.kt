package com.uvg.ana.labfinal.ui.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvg.ana.labfinal.repository.CryptoRepository

class AssetListViewModelFactory(
    private val repository: CryptoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssetListViewModel::class.java)) {
            return AssetListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
