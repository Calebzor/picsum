package hu.tvarga.picsum.detail

import android.os.Build
import hu.tvarga.detail.R
import hu.tvarga.model.dto.PicsumItem
import hu.tvarga.picsum.detail.model.ImageType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class PicsumDetailViewModelTest {

    @Test
    fun `normal download image`() = runBlocking {
        val expectedItem = PicsumItem(
            "0",
            "test author",
            1280,
            1280,
            "https://unsplash.com/photos/yC-Yzbqy7PY",
            "https://picsum.photos/id/0/5616/3744"
        )
        val itemFlow = flowOf(expectedItem)
        val viewModel = PicsumDetailViewModel(mock { onGeneric { invoke(any()) } doReturn itemFlow })

        val first = viewModel.picsums("0").first() // first triggered emission

        assertEquals(expectedItem.id, first.id)
        assertEquals(R.id.normal, first.selected)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `blur download image`() = runBlocking {
        val expectedItem = PicsumItem(
            "0",
            "test author",
            1280,
            1280,
            "https://unsplash.com/photos/yC-Yzbqy7PY",
            "https://picsum.photos/id/0/5616/3744"
        )
        val itemFlow = flowOf(expectedItem)
        val viewModel = PicsumDetailViewModel(mock { onGeneric { invoke(any()) } doReturn itemFlow })

        viewModel.setImageType(ImageType.BLUR).start()

        val item = viewModel.picsums("0").first() // first triggered emission

        assertEquals(expectedItem.id, item.id)
        assertEquals(R.id.blur, item.selected)

    }
}
