package hu.tvarga.picsum.test

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
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
class EmptyListTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class EmpltyListModule {

        @Binds
        abstract fun bindFakePicsumService(fakePicsumService: FakeEmptyPicsumService): PicsumService
    }

    @Test
    fun listIsEmptyForEmptyListTest() {
        ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.list)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            Assert.assertEquals(0, recyclerView.adapter?.itemCount)
        }
    }
}

class FakeEmptyPicsumService @Inject constructor() : PicsumService {
    override suspend fun fetchList(page: Int, limit: Int): List<PicsumApiObject> {
        return emptyList()
    }
}
