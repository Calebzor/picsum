package hu.tvarga.picsum.test

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import hu.tvarga.model.api.PicsumApiObject
import hu.tvarga.picsum.MainActivity
import hu.tvarga.picsum.R
import hu.tvarga.remote.PicsumService
import hu.tvarga.remote.di.ListModule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(ListModule::class)
@HiltAndroidTest
class ListTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeListModule {

        @Binds
        abstract fun bindFakePicsumService(fakePicsumService: FakePicsumService): PicsumService
    }

    @Test
    fun listHasElements() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.list)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            Assert.assertEquals(1, recyclerView.adapter?.itemCount)
        }
    }
}

class FakePicsumService @Inject constructor() : PicsumService {
    override suspend fun fetchList(page: Int, limit: Int): List<PicsumApiObject> {
        if (page > 1) {
            return emptyList()
        }
        return listOf(
            PicsumApiObject(
                "0",
                "test author",
                1280,
                1280,
                "https://unsplash.com/photos/yC-Yzbqy7PY",
                "https://picsum.photos/id/0/5616/3744"
            )
        )
    }

}
