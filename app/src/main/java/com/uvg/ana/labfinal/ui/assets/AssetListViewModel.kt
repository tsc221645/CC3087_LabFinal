package com.uvg.ana.labfinal.ui.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.ana.labfinal.model.Asset
import com.uvg.ana.labfinal.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AssetListViewModel(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets

    private val _offlineSavedState = MutableStateFlow(false)
    val offlineSavedState: StateFlow<Boolean> = _offlineSavedState

    fun loadAssets() {
        viewModelScope.launch {
            try {
                _assets.value = repository.getAssets()
                _offlineSavedState.value = false // Reinicia el estado de guardado
            } catch (e: Exception) {
                _assets.value = emptyList()
            }
        }
    }

    fun saveOffline() {
        viewModelScope.launch {
            try {
                repository.saveAssetsOffline(_assets.value)
                _offlineSavedState.value = true // Indica que los datos fueron guardados
            } catch (e: Exception) {
                _offlineSavedState.value = false // Manejar error si es necesario
            }
        }
    }
}

