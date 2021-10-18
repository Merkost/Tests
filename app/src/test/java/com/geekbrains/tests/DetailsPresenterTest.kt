package com.geekbrains.tests

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.presenter.details.PresenterDetailsContract
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.geekbrains.tests.view.search.ViewSearchContract
import com.nhaarman.mockito_kotlin.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsPresenterTest {

    private var count: Int = 0

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Mock
    private lateinit var presenterContract: PresenterDetailsContract

    private lateinit var scenario: ActivityScenario<DetailsActivity>
    //private lateinit var context: Context

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        //context = ApplicationProvider.getApplicationContext()
        presenter = DetailsPresenter(viewContract, count)
    }

    @Test
    fun presenterSetCounter_NotNull() {
        presenter.setCounter(0)
        assertNotNull(count)
    }

    @Test
    fun presenterSetCounter_isEqual() {
        presenter.setCounter(3)
        assertEquals(3, count) //Doesn't work!!!
    }

    @Test
    fun presenterOnIncrement_isChanging() {
        presenter.onIncrement()
        assertEquals(1, count) //Doesn't work!!!
    }

    @Test
    fun presenterOnIncrement_isViewContractCalled() {
        presenter.onIncrement()
        Mockito.verify(viewContract, times(1)).setCount(count+1)
    }

    @Test
    fun presenterOnDecrement_isViewContractCalled() {
        presenter.onDecrement()
        Mockito.verify(viewContract, times(1)).setCount(count-1)
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun presenterOnAttached_isViewNull() {
        scenario.onActivity {
            assertNotNull(presenter.view)  //Doesn't work!!!
        }
    }

    @Test
    fun presenterOnDetached_isViewNull() {
        scenario.onActivity {
            assertNull(presenter.view)
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}