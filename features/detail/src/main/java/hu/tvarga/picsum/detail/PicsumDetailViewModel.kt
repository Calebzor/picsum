package hu.tvarga.picsum.detail

import android.net.Uri
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tvarga.core.base.BaseViewModel
import hu.tvarga.model.PicsumItem
import hu.tvarga.picsum.detail.model.DetailUiModel
import hu.tvarga.picsum.detail.model.ImageType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PicsumDetailViewModel @Inject constructor(private val getPicsumUseCase: GetPicsumUseCase) : BaseViewModel() {

    private val imageTypeChannel = Channel<ImageType>()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun picsums(id: String) = getPicsumUseCase(id).combine(imageTypeChannel.receiveAsFlow()) { picsum, imageType ->
        detailUiModel(picsum, imageType)
    }

    private fun detailUiModel(
        picsum: PicsumItem,
        imageType: ImageType
    ) = DetailUiModel(
        id = picsum.id,
        author = picsum.author,
        size = "${picsum.width} x ${picsum.height}",
        url = picsum.url,
        downloadUrl = getDownloadUrl(picsum, imageType),
        selected = imageType.id
    )

    private fun getDownloadUrl(picsum: PicsumItem, imageType: ImageType): String {
        val downloadUriBuilder = Uri.parse(picsum.downloadUrl).buildUpon()
        if (imageType == ImageType.BLUR) {
            downloadUriBuilder.query("blur")
        } else if (imageType == ImageType.GREY) {
            downloadUriBuilder.query("grayscale")
        }
        return downloadUriBuilder.build().toString()
    }

    fun setImageType(imageType: ImageType) {
        viewModelScope.launch {
            imageTypeChannel.send(imageType)
        }
    }
}

