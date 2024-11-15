import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.ana.labfinal.model.AssetDetail
import com.uvg.ana.labfinal.repository.CryptoRepository
import com.uvg.ana.labfinal.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AssetDetailViewModel(
    private val repository: CryptoRepository,
    private val assetId: String
) : ViewModel() {

    private val _assetDetail = MutableStateFlow<Resource<AssetDetail>>(Resource.Loading())
    val assetDetail: StateFlow<Resource<AssetDetail>> = _assetDetail

    init {
        loadAssetDetail()
    }

    private fun loadAssetDetail() {
        viewModelScope.launch {
            try {
                val detail = repository.getAssetDetail(assetId)
                _assetDetail.value = Resource.Success(detail)
            } catch (e: Exception) {
                _assetDetail.value = Resource.Error("Error loading asset details")
            }
        }
    }
}
