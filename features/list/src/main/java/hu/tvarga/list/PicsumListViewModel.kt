package hu.tvarga.list

import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import coil.load
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tvarga.core.base.BaseViewModel
import hu.tvarga.list.domain.GetPicsumsUseCase
import hu.tvarga.model.PicsumItem
import hu.tvarga.model.PicsumItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class PicsumListViewModel @Inject constructor(
    private val getPicsumsUseCase: GetPicsumsUseCase
) : BaseViewModel() {

    private var currentResult: Flow<PagingData<PicsumItem>>? = null

    fun getPicsums(): Flow<PagingData<PicsumItem>> {
        val lastResult = currentResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult = getPicsumsUseCase().map { pagingData -> pagingData.map { it.toPicsumItem() } }
            .cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

    fun picsumListItemClick(id: String) {
        Timber.d("picsumListItemClick")
    }

    fun getPicsumImage(imageView: AppCompatImageView, picsum: PicsumItem) {
        imageView.post {
            val width = imageView.width
            val url = picsum.downloadUrl.replace(Regex("\\d+/\\d+$"), "$width")
            imageView.load(url) {
                placeholder(android.R.drawable.ic_menu_report_image)
                size(width, width)
                crossfade(true)
            }
        }
    }
}

private fun PicsumItemEntity.toPicsumItem(): PicsumItem =
    PicsumItem(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        downloadUrl = this.downloadUrl,
    )
