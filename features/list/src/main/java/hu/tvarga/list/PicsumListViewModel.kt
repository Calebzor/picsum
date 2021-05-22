package hu.tvarga.list

import android.R
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tvarga.core.base.BaseViewModel
import hu.tvarga.list.domain.GetPicsumsUseCase
import hu.tvarga.model.dto.PicsumItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


@HiltViewModel
class PicsumListViewModel @Inject constructor(
    getPicsumsUseCase: GetPicsumsUseCase
) : BaseViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val picsums = flowOf(
        flowOf(PagingData.empty()),
        getPicsumsUseCase().cachedIn(viewModelScope)
    ).flattenMerge()

    fun picsumListItemClick(id: String) {
        navigate(ListFragmentDirections.actionListFragmentToDetailFragment(id))
    }

    fun getPicsumImage(imageView: AppCompatImageView, picsum: PicsumItem) {
        val url = picsumImageUrlWithSize(picsum)
        Glide.with(imageView)
            .load(url)
            .placeholder(R.drawable.ic_menu_report_image).into(imageView)
    }

    private fun picsumImageUrlWithSize(picsum: PicsumItem) =
        picsum.downloadUrl.replace(Regex("\\d+/\\d+$"), "$IMAGE_WIDTH/$IMAGE_WIDTH")

    companion object {
        const val IMAGE_WIDTH = 1280
    }
}
