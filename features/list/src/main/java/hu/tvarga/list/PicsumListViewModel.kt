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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class PicsumListViewModel @Inject constructor(
    getPicsumsUseCase: GetPicsumsUseCase
) : BaseViewModel() {


    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val picsums = flowOf(
        clearListCh.receiveAsFlow().map { PagingData.empty() },
        getPicsumsUseCase().map { pagingData -> pagingData.map { it.toPicsumItem() } }.cachedIn(viewModelScope)
    ).flattenMerge(2)

    fun picsumListItemClick(id: String) {
        Timber.d("picsumListItemClick")
    }

    fun getPicsumImage(imageView: AppCompatImageView, picsum: PicsumItem) {
        val url = picsumImageUrlWithSize(picsum)
        imageView.load(url) {
            size(IMAGE_WIDTH, IMAGE_WIDTH)
            placeholder(android.R.drawable.ic_menu_report_image)
        }
    }

    private fun picsumImageUrlWithSize(picsum: PicsumItem) =
        picsum.downloadUrl.replace(Regex("\\d+/\\d+$"), "$IMAGE_WIDTH/$IMAGE_WIDTH")

    companion object {
        const val IMAGE_WIDTH = 1280
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
