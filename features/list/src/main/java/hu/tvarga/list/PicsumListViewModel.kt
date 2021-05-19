package hu.tvarga.list

import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.load
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tvarga.core.base.BaseViewModel
import hu.tvarga.core.coroutine.AppDispatchers
import hu.tvarga.core.resource.Resource
import hu.tvarga.core.util.Event
import hu.tvarga.list.domain.GetPicsumsUseCase
import hu.tvarga.model.PicsumItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class PicsumListViewModel @Inject constructor(
    private val getPicsumsUseCase: GetPicsumsUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val _picsums = MediatorLiveData<Resource<List<PicsumItem>>>()
    val picsums: LiveData<Resource<List<PicsumItem>>> get() = _picsums
    private var eucListSource: LiveData<Resource<List<PicsumItem>>> = MutableLiveData()

    fun getpicsums(forceRefresh: Boolean) = viewModelScope.launch(dispatchers.main) {
        _picsums.removeSource(eucListSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        withContext(dispatchers.io) { eucListSource = getPicsumsUseCase(forceRefresh = forceRefresh) }
        _picsums.addSource(eucListSource) {
            _picsums.value = it
            if (it.status == Resource.Status.ERROR) _snackbarError.value = Event(R.string.an_error_happened)
        }
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
