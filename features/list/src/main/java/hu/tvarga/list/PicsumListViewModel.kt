package hu.tvarga.list

import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import coil.load
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tvarga.core.base.BaseViewModel
import hu.tvarga.list.domain.GetPicsumsUseCase
import hu.tvarga.model.PicsumItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


@HiltViewModel
class PicsumListViewModel @Inject constructor(
    getPicsumsUseCase: GetPicsumsUseCase
) : BaseViewModel() {


    private val clearListChannel = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val picsums = flowOf(
        clearListChannel.receiveAsFlow().map { PagingData.empty() },
        getPicsumsUseCase().cachedIn(viewModelScope)
    ).flattenMerge(2)

    fun picsumListItemClick(id: String) {
        navigate(ListFragmentDirections.actionListFragmentToDetailFragment(id))
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
