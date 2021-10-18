package com.geekbrains.tests.presenter.details

import android.view.View
import com.geekbrains.tests.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
    override fun onAttach(view: View)
    override fun onDetach()
}
